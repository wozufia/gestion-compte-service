package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.CompteDto;
import com.awa.centrale.gestioncompte.dto.ContactDto;
import com.awa.centrale.gestioncompte.model.Compte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompteMapperTest {

    @Autowired
    private CompteMapper compteMapper;

    @Test
    void testMapCompteFromDto() {
        ContactDto dtoContact = new ContactDto();
        dtoContact.setEmail("contact@example.com");

        CompteDto dto = new CompteDto();
        dto.setNom("Test Account");
        dto.setApplication("TestApp");
        dto.setStatus("ACTIVE");
        dto.setContact(dtoContact);

        Compte model = compteMapper.toModel(dto);

        assertEquals("Test Account", model.getNom());
        assertEquals("TestApp", model.getApplication());
        assertEquals("ACTIVE", model.getStatus());
        assertNotNull(model.getContact());
        assertEquals("contact@example.com", model.getContact().getEmail());
        assertNull(model.getId());
    }

    @Test
    void testMapCompteFromDtoNull() {
        Compte model = compteMapper.toModel(null);
        assertNull(model);
    }

    @Test
    void testMapCompteIdIgnored() {
        CompteDto dto = new CompteDto();
        dto.setNom("Test");
        dto.setApplication("App");

        Compte model = compteMapper.toModel(dto);

        assertNull(model.getId());
    }
}
