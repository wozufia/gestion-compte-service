package com.awa.centrale.gestioncompte.dao;

import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import com.awa.centrale.gestioncompte.utils.Default;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GestionAccesRepositoryTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private GestionAccesRepository gestionAccesRepository;

    @Test
    void obtenirUtilisateurParEmailEtMotDePasse_shouldDelegateToRepository() {
        Utilisateur expected = new Utilisateur();
        expected.setEmail("user@example.com");
        expected.setMotDePasse("Secret123");

        when(utilisateurRepository.findByEmailAndMotDePasseAndActiveTrue("user@example.com", "Secret123")).thenReturn(expected);

        Utilisateur result = gestionAccesRepository.obtenirUtilisateurParEmailEtMotDePasse("user@example.com", "Secret123");

        assertNotNull(result);
        assertEquals("user@example.com", result.getEmail());
        verify(utilisateurRepository).findByEmailAndMotDePasseAndActiveTrue("user@example.com", "Secret123");
    }

    @Test
    void obtenirUtilisateurParEmail_shouldDelegateToRepository() {
        Utilisateur expected = new Utilisateur();
        expected.setEmail("user@example.com");

        when(utilisateurRepository.findByEmailAndActiveTrue("user@example.com")).thenReturn(expected);

        Utilisateur result = gestionAccesRepository.obtenirUtilisateurParEmail("user@example.com");

        assertNotNull(result);
        assertEquals("user@example.com", result.getEmail());
        verify(utilisateurRepository).findByEmailAndActiveTrue("user@example.com");
    }

    @Test
    void obtenirUtilisateurParId_shouldDelegateToRepository() {
        Utilisateur expected = new Utilisateur();
        expected.setId(42);
        expected.setEmail("id-user@example.com");

        when(utilisateurRepository.findByIdAndActiveTrue(42)).thenReturn(expected);

        Utilisateur result = gestionAccesRepository.obtenirUtilisateurParId(42);

        assertNotNull(result);
        assertEquals(42, result.getId());
        assertEquals("id-user@example.com", result.getEmail());
        verify(utilisateurRepository).findByIdAndActiveTrue(42);
    }

    @Test
    void sauvegarderUtilisateur_shouldDelegateToRepositorySave() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("save@example.com");
        utilisateur.setRoles(Set.of(Default.defaultRole()));

        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);

        Utilisateur result = gestionAccesRepository.sauvegarderUtilisateur(utilisateur);

        assertNotNull(result);
        assertEquals("save@example.com", result.getEmail());
        verify(utilisateurRepository).save(utilisateur);
    }

    @Test
    void ajouterRolesAUtilisateurEtActiver_shouldSetRolesAndActiveFlagThenSave() {
        Utilisateur existing = new Utilisateur();
        existing.setEmail("user@example.com");
        existing.setActive(false);
        Set<Role> roles = new HashSet<>();
        Role adminRole = new Role();
        adminRole.setNom("ADMIN");
        Role userRole = new Role();
        userRole.setNom("USER");
        roles.add(adminRole);
        roles.add(userRole);

        when(utilisateurRepository.findByEmailAndActiveTrue("user@example.com")).thenReturn(existing);
        when(utilisateurRepository.save(existing)).thenReturn(existing);

        Utilisateur result = gestionAccesRepository.ajouterRolesAUtilisateurEtActiver("user@example.com", roles);

        assertNotNull(result);
        assertEquals("user@example.com", result.getEmail());
        assertEquals(Boolean.TRUE, result.getActive());
        assertEquals(2, result.getRoles().size());
        verify(utilisateurRepository).findByEmailAndActiveTrue("user@example.com");
        verify(utilisateurRepository).save(existing);
    }

    @Test
    void obtenirUtilisateurParEmailEtMotDePasse_shouldReturnNullWhenNotFound() {
        when(utilisateurRepository.findByEmailAndMotDePasseAndActiveTrue("missing@example.com", "badpass")).thenReturn(null);

        Utilisateur result = gestionAccesRepository.obtenirUtilisateurParEmailEtMotDePasse("missing@example.com", "badpass");

        assertNull(result);
    }
}
