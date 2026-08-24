package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.CreationRoleRequeteDto;
import com.awa.centrale.gestioncompte.model.CreationRoleRequete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CreationRoleRequeteMapperTest {

    @Autowired
    private CreationRoleRequeteMapper creationRoleRequeteMapper;

    @Test
    void testMapCreationRoleRequeteFromDto() {
        CreationRoleRequeteDto dto = new CreationRoleRequeteDto();
        dto.setName("MODERATOR");
        dto.setDescription("Moderator Role");

        CreationRoleRequete model = creationRoleRequeteMapper.toModel(dto);

        assertEquals("MODERATOR", model.getName());
        assertEquals("Moderator Role", model.getDescription());
    }

    @Test
    void testMapCreationRoleRequeteFromDtoNull() {
        CreationRoleRequete model = creationRoleRequeteMapper.toModel(null);
        assertNull(model);
    }
}
