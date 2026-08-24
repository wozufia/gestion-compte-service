package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.CreationUtilisateurRequeteDto;
import com.awa.centrale.gestioncompte.model.CreationUtilisateurRequete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CreationUtilisateurRequeteMapperTest {

    @Autowired
    private CreationUtilisateurRequeteMapper creationUtilisateurRequeteMapper;

    @Test
    void testMapCreationUtilisateurRequeteFromDto() {
        CreationUtilisateurRequeteDto dto = new CreationUtilisateurRequeteDto();
        dto.setEmail("newuser@example.com");
        dto.setFirstName("Jane");
        dto.setLastName("Smith");
        dto.setMotDePasse("SecurePass123");

        CreationUtilisateurRequete model = creationUtilisateurRequeteMapper.toModel(dto);

        assertEquals("newuser@example.com", model.getEmail());
        assertEquals("Jane", model.getFirstName());
        assertEquals("Smith", model.getLastName());
        assertEquals("SecurePass123", model.getMotDePasse());
    }

    @Test
    void testMapCreationUtilisateurRequeteFromDtoNull() {
        CreationUtilisateurRequete model = creationUtilisateurRequeteMapper.toModel(null);
        assertNull(model);
    }
}
