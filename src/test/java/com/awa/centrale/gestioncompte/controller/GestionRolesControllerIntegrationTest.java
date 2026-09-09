package com.awa.centrale.gestioncompte.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.awa.centrale.gestioncompte.dto.CreationRoleRequeteDto;
import com.awa.centrale.gestioncompte.dto.RoleDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
class GestionRolesControllerIntegrationTest {

    @Autowired
    private GestionRolesController gestionRolesController;

    @Test
    void listerRoles_shouldReturnExistingRoles() {
        List<RoleDto> roles = gestionRolesController.listerRoles();

        assertNotNull(roles);
        assertFalse(roles.isEmpty());
        assertEquals("ADMIN", roles.getFirst().getNom());
    }

    @Test
    void creerRole_shouldPersistRole() {
        String nom = "ROLE_INTEGRATION_" + System.currentTimeMillis();

        CreationRoleRequeteDto requete = new CreationRoleRequeteDto();
        requete.setNom(nom);
        requete.setDescription("Rôle créé en intégration");

        RoleDto roleCree = gestionRolesController.creerRole(requete);

        assertNotNull(roleCree);
        assertEquals(nom, roleCree.getNom());
        assertEquals("Rôle créé en intégration", roleCree.getDescription());
    }
}
