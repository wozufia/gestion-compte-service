package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.ErreurDto;
import com.awa.centrale.gestioncompte.model.Erreur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ErreurMapperTest {

    @Autowired
    private ErreurMapper erreurMapper;

    @Test
    void testMapErreurFromDto() {
        ErreurDto dto = new ErreurDto();
        dto.setStatus("ERROR");
        dto.setHttpStatus(400);
        dto.setMessage("Bad Request");

        Erreur model = erreurMapper.toModel(dto);

        assertEquals("ERROR", model.getStatus());
        assertEquals(400, model.getHttpStatus());
        assertEquals("Bad Request", model.getMessage());
    }

    @Test
    void testMapErreurFromDtoNull() {
        Erreur model = erreurMapper.toModel(null);
        assertNull(model);
    }
}
