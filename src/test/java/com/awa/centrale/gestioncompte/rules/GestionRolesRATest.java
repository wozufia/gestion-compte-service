package com.awa.centrale.gestioncompte.rules;

import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.utils.Default;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GestionRolesRATest {

    @Test
    void obtenirRolesDeRequete_shouldReturnDefaultRoleWhenInputIsNull() {
        Set<Role> roles = GestionRolesRA.obtenirRolesDeRequete(null);

        assertNotNull(roles);
        assertEquals(1, roles.size());
        assertTrue(roles.stream().anyMatch(role -> Default.USER_ROLE_NAME.equals(role.getNom())));
    }

    @Test
    void obtenirRolesDeRequete_shouldDeduplicateAndNormalizeRoles() {
        Set<String> input = Set.of(" admin ", "USER", "user", "ADMIN");

        Set<Role> roles = GestionRolesRA.obtenirRolesDeRequete(input);

        assertEquals(Set.of("ADMIN", "USER"), roles.stream().map(Role::getNom).collect(Collectors.toSet()));
    }

    @Test
    void listerRolesDeRequete_shouldTrimAndUppercaseRoles() {
        Set<String> roles = GestionRolesRA.listerRolesDeRequete(" admin , user , , ADMIN ");

        assertEquals(Set.of("ADMIN", "USER"), roles);
    }

    @Test
    void listerRolesDeRequete_shouldReturnEmptySetWhenInputIsNullOrBlank() {
        assertTrue(GestionRolesRA.listerRolesDeRequete(null).isEmpty());
        assertTrue(GestionRolesRA.listerRolesDeRequete("   ").isEmpty());
    }
}
