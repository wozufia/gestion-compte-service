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
    void testMapAuthReponseFromDto() {
        AuthReponseDto dto = new AuthReponseDto();
        dto.setAccessToken("access_token_xyz");
        dto.setRefreshToken("refresh_token_abc");
        dto.setTokenType("Bearer");
        dto.setExpiresIn(3600);

        AuthReponse model = authReponseMapper.toModel(dto);

        assertEquals("access_token_xyz", model.getAccessToken());
        assertEquals("refresh_token_abc", model.getRefreshToken());
        assertEquals("Bearer", model.getTokenType());
        assertEquals(3600, model.getExpiresIn());
    }

    @Test
    void testMapAuthReponseFromDtoNull() {
        AuthReponse model = authReponseMapper.toModel(null);
        assertNull(model);
    }
}
