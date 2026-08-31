package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.dto.CompteDto;
import com.awa.centrale.gestioncompte.dto.UtilisateurDto;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UtilisateurMapperTest {

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Test
    void testMapUtilisateurFromDto() {
        CompteDto dtoCompte = new CompteDto();
        dtoCompte.setNom("Main Account");

        UtilisateurDto dto = new UtilisateurDto();
        dto.setEmail("user@example.com");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setActive(true);
        dto.setCompte(dtoCompte);

        Utilisateur model = utilisateurMapper.toModel(dto);

        assertEquals("user@example.com", model.getEmail());
        assertEquals("John", model.getFirstName());
        assertEquals("Doe", model.getLastName());
        assertTrue(model.getActive());
        assertNotNull(model.getCompte());
        assertEquals("Main Account", model.getCompte().getNom());
        assertNull(model.getId());
    }

    @Test
    void testMapUtilisateurFromDtoNull() {
        Utilisateur model = utilisateurMapper.toModel(null);
        assertNull(model);
    }

    @Test
    void testMapUtilisateurIdIgnored() {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setEmail("test@test.com");

        Utilisateur model = utilisateurMapper.toModel(dto);

        assertNull(model.getId());
    }
}
