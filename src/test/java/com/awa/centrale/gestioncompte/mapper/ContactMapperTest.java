package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.AdresseDto;
import com.awa.centrale.gestioncompte.dto.ContactDto;
import com.awa.centrale.gestioncompte.dto.TelephoneDto;
import com.awa.centrale.gestioncompte.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContactMapperTest {

    @Autowired
    private ContactMapper contactMapper;

    @Test
    void testMapContactFromDto() {
        TelephoneDto dtoTelephone = new TelephoneDto();
        dtoTelephone.setIndicatif("+33");
        dtoTelephone.setNumero("612345678");

        AdresseDto dtoAdresse = new AdresseDto();
        dtoAdresse.setRue("123 Rue Test");
        dtoAdresse.setVille("Lyon");
        dtoAdresse.setCodePostal("69000");

        ContactDto dto = new ContactDto();
        dto.setTelephone(dtoTelephone);
        dto.setEmail("test@example.com");
        dto.setAdresse(dtoAdresse);

        Contact model = contactMapper.toModel(dto);

        assertNotNull(model);
        assertEquals("test@example.com", model.getEmail());
        assertNotNull(model.getTelephone());
        assertEquals("+33", model.getTelephone().getIndicatif());
        assertEquals("612345678", model.getTelephone().getNumero());
        assertNotNull(model.getAdresse());
        assertEquals("123 Rue Test", model.getAdresse().getRue());
        assertEquals("Lyon", model.getAdresse().getVille());
        assertEquals("69000", model.getAdresse().getCodePostal());
    }

    @Test
    void testMapContactFromDtoNull() {
        Contact model = contactMapper.toModel(null);
        assertNull(model);
    }

    @Test
    void testMapContactWithNullNestedObjects() {
        ContactDto dto = new ContactDto();
        dto.setTelephone(null);
        dto.setEmail("test@example.com");
        dto.setAdresse(null);

        Contact model = contactMapper.toModel(dto);

        assertEquals("test@example.com", model.getEmail());
        assertNull(model.getTelephone());
        assertNull(model.getAdresse());
    }
}
