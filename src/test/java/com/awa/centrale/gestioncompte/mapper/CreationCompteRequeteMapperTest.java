package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.ContactDto;
import com.awa.centrale.gestioncompte.dto.CreationCompteRequeteDto;
import com.awa.centrale.gestioncompte.model.CreationCompteRequete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CreationCompteRequeteMapperTest {

    @Autowired
    private CreationCompteRequeteMapper creationCompteRequeteMapper;

    @Test
    void testMapCreationCompteRequeteFromDto() {
        ContactDto dtoContact = new ContactDto();
        dtoContact.setEmail("admin@company.com");

        CreationCompteRequeteDto dto = new CreationCompteRequeteDto();
        dto.setNom("New Company Account");
        dto.setContact(dtoContact);

        CreationCompteRequete model = creationCompteRequeteMapper.toModel(dto);

        assertEquals("New Company Account", model.getNom());
        assertNotNull(model.getContact());
        assertEquals("admin@company.com", model.getContact().getEmail());
    }

    @Test
    void testMapCreationCompteRequeteFromDtoNull() {
        CreationCompteRequete model = creationCompteRequeteMapper.toModel(null);
        assertNull(model);
    }
}
