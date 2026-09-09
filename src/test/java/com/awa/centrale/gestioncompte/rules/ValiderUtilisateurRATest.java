package com.awa.centrale.gestioncompte.rules;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValiderUtilisateurRATest {

    @Mock
    private GestionAccesRepository gestionAccesRepository;

    @Test
    void obtenirNouveauxRoles_shouldReturnEmptySetWhenUserDoesNotExist() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("new@example.com");
        utilisateur.setRoles(Set.of());

        when(gestionAccesRepository.obtenirUtilisateurParEmail("new@example.com")).thenReturn(null);

        Set<Role> result = assertDoesNotThrow(() -> ValiderUtilisateurRA.obtenirNouveauxRoles(utilisateur, gestionAccesRepository));

        assertEquals(Set.of(), result);
    }

    @Test
    void obtenirNouveauxRoles_shouldThrowWhenExistingUserHasSameRole() {
        Utilisateur newUser = new Utilisateur();
        newUser.setEmail("existing@example.com");
        Role newRole = new Role();
        newRole.setNom("USER_API0");
        newUser.setRoles(Set.of(newRole));

        Utilisateur existingUser = new Utilisateur();
        existingUser.setEmail("existing@example.com");
        Role existingRole = new Role();
        existingRole.setNom("USER_API0");
        existingUser.setRoles(Set.of(existingRole));

        when(gestionAccesRepository.obtenirUtilisateurParEmail("existing@example.com")).thenReturn(existingUser);

        assertThrows(IllegalArgumentException.class,
                () -> ValiderUtilisateurRA.obtenirNouveauxRoles(newUser, gestionAccesRepository));
    }

    @Test
    void obtenirNouveauxRolesUtilisateur_shouldReturnNewRolesWhenDifferent() {
        Utilisateur newUser = new Utilisateur();
        newUser.setEmail("mixed@example.com");
        Role newRole = new Role();
        newRole.setNom("ADMIN_API1");
        newUser.setRoles(Set.of(newRole));

        Utilisateur existingUser = new Utilisateur();
        existingUser.setEmail("mixed@example.com");
        Role existingRole = new Role();
        existingRole.setNom("USER_API0");
        existingUser.setRoles(Set.of(existingRole));

        when(gestionAccesRepository.obtenirUtilisateurParEmail("mixed@example.com")).thenReturn(existingUser);

        Set<Role> result = ValiderUtilisateurRA.obtenirNouveauxRoles(newUser, gestionAccesRepository);

        assertEquals(Set.of(newRole,existingRole), result);
    }
}
