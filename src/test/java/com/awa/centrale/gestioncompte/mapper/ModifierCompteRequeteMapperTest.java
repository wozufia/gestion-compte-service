package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.ContactDto;
import com.awa.centrale.gestioncompte.dto.ModifierCompteRequeteDto;
import com.awa.centrale.gestioncompte.model.ModifierCompteRequete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModifierCompteRequeteMapperTest {

    @Autowired
    private ModifierCompteRequeteMapper modifierCompteRequeteMapper;

    @Test
    void testMapModifierCompteRequeteFromDto() {
        ContactDto dtoContact = new ContactDto();
        dtoContact.setEmail("newemail@company.com");

        ModifierCompteRequeteDto dto = new ModifierCompteRequeteDto();
        dto.setNom("Updated Account Name");
        dto.setContact(dtoContact);

        ModifierCompteRequete model = modifierCompteRequeteMapper.toModel(dto);

        assertEquals("Updated Account Name", model.getNom());
        assertNotNull(model.getContact());
        assertEquals("newemail@company.com", model.getContact().getEmail());
    }

    @Test
    void testMapModifierCompteRequeteFromDtoNull() {
        ModifierCompteRequete model = modifierCompteRequeteMapper.toModel(null);
        assertNull(model);
    }
}
