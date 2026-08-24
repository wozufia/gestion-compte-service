package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.ConnectionRequeteDto;
import com.awa.centrale.gestioncompte.model.ConnectionRequete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConnectionRequeteMapperTest {

    @Autowired
    private ConnectionRequeteMapper connectionRequeteMapper;

    @Test
    void testMapConnectionRequeteFromDto() {
        ConnectionRequeteDto dto = new ConnectionRequeteDto();
        dto.setEmail("login@example.com");
        dto.setMotDePasse("password123");

        ConnectionRequete model = connectionRequeteMapper.toModel(dto);

        assertEquals("login@example.com", model.getEmail());
        assertEquals("password123", model.getMotDePasse());
    }

    @Test
    void testMapConnectionRequeteFromDtoNull() {
        ConnectionRequete model = connectionRequeteMapper.toModel(null);
        assertNull(model);
    }
}
