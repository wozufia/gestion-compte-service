package com.awa.centrale.gestioncompte.service.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.model.Role;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListerRolesServiceTest {

    @Mock
    private GestionAccesRepository gestionAccesRepository;

    @InjectMocks
    private ListerRolesService listerRolesService;

    @Test
    void listerRoles_shouldDelegateToDaoAndReturnRoles() {
        Role admin = new Role();
        admin.setNom("ADMIN");
        admin.setDescription("Accès complet");

        Role user = new Role();
        user.setNom("USER");
        user.setDescription("Accès simple");

        List<Role> expectedRoles = List.of(admin, user);
        when(gestionAccesRepository.listerRoles()).thenReturn(expectedRoles);

        List<Role> result = listerRolesService.listerRoles();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedRoles, result);
        verify(gestionAccesRepository).listerRoles();
    }
}
