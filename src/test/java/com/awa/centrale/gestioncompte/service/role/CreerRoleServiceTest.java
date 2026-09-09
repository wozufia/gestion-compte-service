package com.awa.centrale.gestioncompte.service.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.model.CreationRoleRequete;
import com.awa.centrale.gestioncompte.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreerRoleServiceTest {

    @Mock
    private GestionAccesRepository gestionAccesRepository;

    @InjectMocks
    private CreerRoleService creerRoleService;

    @Test
    void creerRole_shouldMapRequestAndPersistRole() {
        CreationRoleRequete requete = new CreationRoleRequete();
        requete.setNom("ADMIN");
        requete.setDescription("Gestionnaire administratif");

        Role expectedRole = new Role();
        expectedRole.setNom("ADMIN");
        expectedRole.setDescription("Gestionnaire administratif");

        when(gestionAccesRepository.sauverRole(any(Role.class))).thenReturn(expectedRole);

        Role result = creerRoleService.creerRole(requete);

        assertNotNull(result);
        assertEquals("ADMIN", result.getNom());
        assertEquals("Gestionnaire administratif", result.getDescription());
        verify(gestionAccesRepository).sauverRole(any(Role.class));
    }
}
