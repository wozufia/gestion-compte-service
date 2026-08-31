package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.AuthReponseDto;
import com.awa.centrale.gestioncompte.model.AuthReponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthReponseMapperTest {

    @Autowired
    private AuthReponseMapper authReponseMapper;

    @Test
    void testMapAuthReponseFromModel() {
        AuthReponse model = new AuthReponse();
        model.setJwt("access_token_xyz");
        model.setTokenType("Bearer");

        AuthReponseDto dto = authReponseMapper.toDto(model);

        assertEquals("access_token_xyz", dto.getJwt());
        assertEquals("Bearer", dto.getTokenType());
    }

    @Test
    void testMapAuthReponseFromModelNull() {
        AuthReponseDto dto = authReponseMapper.toDto(null);
        assertNull(dto);
    }
}
