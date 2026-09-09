package com.awa.centrale.gestioncompte.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dto.CreationRoleRequeteDto;
import com.awa.centrale.gestioncompte.dto.RoleDto;
import com.awa.centrale.gestioncompte.mapper.CreationRoleRequeteMapper;
import com.awa.centrale.gestioncompte.mapper.RoleMapper;
import com.awa.centrale.gestioncompte.model.CreationRoleRequete;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.service.role.CreerRoleService;
import com.awa.centrale.gestioncompte.service.role.ListerRolesService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GestionRolesControllerTest {

    @Mock
    private RoleMapper roleMapper;

    @Mock
    private CreationRoleRequeteMapper creationRoleRequeteMapper;

    @Mock
    private ListerRolesService listerRolesService;

    @Mock
    private CreerRoleService creerRoleService;

    @InjectMocks
    private GestionRolesController gestionRolesController;

    @Test
    void listerRoles_shouldReturnMappedRoles() {
        Role admin = new Role();
        admin.setNom("ADMIN");
        admin.setDescription("Administrateur");

        RoleDto adminDto = new RoleDto();
        adminDto.setNom("ADMIN");
        adminDto.setDescription("Administrateur");

        when(listerRolesService.listerRoles()).thenReturn(List.of(admin));
        when(roleMapper.toDtoList(List.of(admin))).thenReturn(List.of(adminDto));

        List<RoleDto> result = gestionRolesController.listerRoles();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ADMIN", result.getFirst().getNom());
        verify(listerRolesService).listerRoles();
        verify(roleMapper).toDtoList(List.of(admin));
    }

    @Test
    void creerRole_shouldMapRequestAndPersistRole() {
        CreationRoleRequeteDto dto = new CreationRoleRequeteDto();
        dto.setNom("USAGER");
        dto.setDescription("Utilisateur public");

        CreationRoleRequete model = new CreationRoleRequete();
        model.setNom("USAGER");
        model.setDescription("Utilisateur public");

        Role role = new Role();
        role.setNom("USAGER");
        role.setDescription("Utilisateur public");

        RoleDto roleDto = new RoleDto();
        roleDto.setNom("USAGER");
        roleDto.setDescription("Utilisateur public");

        when(creationRoleRequeteMapper.toModel(dto)).thenReturn(model);
        when(creerRoleService.creerRole(model)).thenReturn(role);
        when(roleMapper.toDto(role)).thenReturn(roleDto);

        RoleDto result = gestionRolesController.creerRole(dto);

        assertNotNull(result);
        assertEquals("USAGER", result.getNom());
        assertEquals("Utilisateur public", result.getDescription());
        verify(creationRoleRequeteMapper).toModel(dto);
        verify(creerRoleService).creerRole(model);
        verify(roleMapper).toDto(role);
    }
}
