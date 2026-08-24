package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.ModifierUtilisateurRequeteDto;
import com.awa.centrale.gestioncompte.model.ModifierUtilisateurRequete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModifierUtilisateurRequeteMapperTest {

    @Autowired
    private ModifierUtilisateurRequeteMapper modifierUtilisateurRequeteMapper;

    @Test
    void testMapModifierUtilisateurRequeteFromDto() {
        ModifierUtilisateurRequeteDto dto = new ModifierUtilisateurRequeteDto();
        dto.setEmail("updated@example.com");
        dto.setFirstName("Updated");
        dto.setLastName("Name");
        dto.setMotDePasse("NewPassword123");

        ModifierUtilisateurRequete model = modifierUtilisateurRequeteMapper.toModel(dto);

        assertEquals("updated@example.com", model.getEmail());
        assertEquals("Updated", model.getFirstName());
        assertEquals("Name", model.getLastName());
        assertEquals("NewPassword123", model.getMotDePasse());
    }

    @Test
    void testMapModifierUtilisateurRequeteFromDtoNull() {
        ModifierUtilisateurRequete model = modifierUtilisateurRequeteMapper.toModel(null);
        assertNull(model);
    }
}
