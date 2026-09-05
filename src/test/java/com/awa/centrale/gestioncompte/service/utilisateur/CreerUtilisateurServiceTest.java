package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.UtilisateurDao;
import com.awa.centrale.gestioncompte.model.CreationUtilisateurRequete;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import com.awa.centrale.gestioncompte.utils.Default;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreerUtilisateurServiceTest {

    @Mock
    private UtilisateurDao utilisateurDao;

    @InjectMocks
    private CreerUtilisateurService creerUtilisateurService;

    @Test
    void creerUtilisateur_utiliseLeRoleParDefaut() {
        CreationUtilisateurRequete requete = new CreationUtilisateurRequete();
        requete.setEmail("user@example.com");
        requete.setFirstName("Jean");
        requete.setLastName("Dupont");
        requete.setMotDePasse("SecurePass123");
        requete.setRoles(null);

        when(utilisateurDao.obtenirUtilisateurParEmail("user@example.com")).thenReturn(null);
        when(utilisateurDao.sauvegarderUtilisateur(any())).thenAnswer(invocation -> {
            Utilisateur utilisateurArg = invocation.getArgument(0);
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setEmail(utilisateurArg.getEmail());
            Role role = new Role();
            role.setNom(Default.USER_ROLE_NAME);
            utilisateur.setRoles(Set.of(role));
            return utilisateur;
        });

        Utilisateur utilisateur = creerUtilisateurService.creerUtilisateur(requete);

        assertNotNull(utilisateur);
        assertNotNull(utilisateur.getRoles());
        assertEquals(Set.of(Default.USER_ROLE_NAME), utilisateur.getRoles().stream().map(Role::getNom).collect(Collectors.toSet()));
        verify(utilisateurDao).sauvegarderUtilisateur(any(Utilisateur.class));
    }

    @Test
    void creerUtilisateur_deduplicateLesRoles() {
        CreationUtilisateurRequete requete = new CreationUtilisateurRequete();
        requete.setEmail("admin@example.com");
        requete.setFirstName("Admin");
        requete.setLastName("System");
        requete.setMotDePasse("Secret123");
        requete.setRoles("ADMIN, USER, ADMIN");

        when(utilisateurDao.obtenirUtilisateurParEmail("admin@example.com")).thenReturn(null);
        when(utilisateurDao.sauvegarderUtilisateur(any())).thenAnswer(invocation -> {
            Utilisateur utilisateurArg = invocation.getArgument(0);
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setEmail(utilisateurArg.getEmail());
            Role adminRole = new Role();
            adminRole.setNom("ADMIN");
            Role userRole = new Role();
            userRole.setNom("USER");
            utilisateur.setRoles(Set.of(adminRole, userRole));
            return utilisateur;
        });

        Utilisateur utilisateur = creerUtilisateurService.creerUtilisateur(requete);

        assertNotNull(utilisateur.getRoles());
        assertEquals(Set.of("ADMIN", "USER"), utilisateur.getRoles().stream().map(Role::getNom).collect(Collectors.toSet()));
        verify(utilisateurDao).sauvegarderUtilisateur(any(Utilisateur.class));
    }

    @Test
    void creerUtilisateur_RetourneErreurQuandUtilisateurExistantALeMemeRole() {
        CreationUtilisateurRequete requete = new CreationUtilisateurRequete();
        requete.setEmail("existing@example.com");
        requete.setFirstName("Existing");
        requete.setLastName("User");
        requete.setMotDePasse("Password123");
        requete.setRoles("USER");

        Utilisateur existingUser = new Utilisateur();
        existingUser.setEmail("existing@example.com");
        Role existingRole = new Role();
        existingRole.setNom("USER");
        existingUser.setRoles(Set.of(existingRole));

        when(utilisateurDao.obtenirUtilisateurParEmail("existing@example.com")).thenReturn(existingUser);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> creerUtilisateurService.creerUtilisateur(requete)
        );

        assertEquals(
                "Un utilisateur existant possède déjà un rôle identique à celui que vous tentez de créer.",
                exception.getMessage()
        );
        verify(utilisateurDao, never()).sauvegarderUtilisateur(any(Utilisateur.class));
        verify(utilisateurDao, never()).ajouterRolesAUtilisateurEtActiver(any(), any());
    }

    @Test
    void creerUtilisateur_shouldAllowSameEmailWithDifferentRole() {
        CreationUtilisateurRequete requete = new CreationUtilisateurRequete();
        requete.setEmail("mixed@example.com");
        requete.setFirstName("Mixed");
        requete.setLastName("Account");
        requete.setMotDePasse("Password123");
        requete.setRoles("ADMIN");

        Utilisateur utilisateurExistant = new Utilisateur();
        utilisateurExistant.setEmail("mixed@example.com");
        Role existingRole = new Role();
        existingRole.setNom("USER");
        utilisateurExistant.setRoles(Set.of(existingRole));

        Utilisateur utilisateurAttendu= new Utilisateur();
        utilisateurAttendu.setEmail("mixed@example.com");
        Role utilisateurAttenduRole1 = new Role();
        utilisateurAttenduRole1.setNom("ADMIN");
        Role utilisateurAttenduRole2 = new Role();
        utilisateurAttenduRole2.setNom("USER");
        utilisateurAttendu.setRoles(Set.of(utilisateurAttenduRole1, utilisateurAttenduRole2));

        when(utilisateurDao.obtenirUtilisateurParEmail("mixed@example.com")).thenReturn(utilisateurExistant);
        when(utilisateurDao.ajouterRolesAUtilisateurEtActiver(any(String.class),any())).thenReturn(utilisateurAttendu);

        Utilisateur utilisateur = creerUtilisateurService.creerUtilisateur(requete);

        assertNotNull(utilisateur);
        assertEquals(Set.of("ADMIN", "USER"), utilisateur.getRoles().stream().map(Role::getNom).collect(Collectors.toSet()));
        verify(utilisateurDao).ajouterRolesAUtilisateurEtActiver(any(String.class), ArgumentMatchers.<Set<Role>>any());
    }
}
