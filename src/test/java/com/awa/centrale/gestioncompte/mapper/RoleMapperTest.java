package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.awa.centrale.gestioncompte.dto.RoleDto;
import com.awa.centrale.gestioncompte.model.Role;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    void testMapRoleFromDto() {
        RoleDto dto = new RoleDto();
        dto.setName("ADMIN_API1");
        dto.setDescription("Administrateur de l'application API1");

        Role model = roleMapper.toModel(dto);

        assertEquals("ADMIN_API1", model.getName());
        assertEquals("Administrateur de l'application API1", model.getDescription());
        assertNull(model.getId());
    }

    @Test
    void testMapRoleFromDtoNull() {
        Role model = roleMapper.toModel(null);
        assertNull(model);
    }

    @Test
    void testMapRoleListFromDto() {
        RoleDto dtoRole1 = new RoleDto();
        dtoRole1.setName("ADMIN");
        dtoRole1.setDescription("Admin");

        RoleDto dtoRole2 = new RoleDto();
        dtoRole2.setName("USER");
        dtoRole2.setDescription("User");

        List<RoleDto> dtoRoles = Arrays.asList(dtoRole1, dtoRole2);

        List<Role> models = roleMapper.toModelList(dtoRoles);

        assertNotNull(models);
        assertEquals(2, models.size());
        assertEquals("ADMIN", models.get(0).getName());
        assertEquals("USER", models.get(1).getName());
    }

    @Test
    void testMapRoleListNull() {
        List<Role> models = roleMapper.toModelList(null);
        assertNull(models);
    }

    @Test
    void testMapRoleListEmpty() {
        List<RoleDto> dtoRoles = List.of();
        List<Role> models = roleMapper.toModelList(dtoRoles);

        assertNotNull(models);
        assertTrue(models.isEmpty());
    }
}
