package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.awa.centrale.gestioncompte.dto.ObjetErreurDto;
import com.awa.centrale.gestioncompte.model.ObjetErreur;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjetErreurMapperTest {

    @Autowired
    private ObjetErreurMapper objetErreurMapper;

    @Test
    void testMapObjetErreurFromDto() {
        ObjetErreurDto dto = new ObjetErreurDto();
        dto.setException("NullPointerException");
        dto.setSystemId("SYS001");
        dto.setCode("ERR_NULL");
        dto.setSystemName("UserService");
        dto.setMeta("Additional metadata");
        dto.setDetail("Null pointer in method X");
        dto.setTime("2024-08-24T10:00:00Z");
        dto.setMessage("A null reference error occurred");

        ObjetErreur model = objetErreurMapper.toModel(dto);

        assertEquals("NullPointerException", model.getException());
        assertEquals("SYS001", model.getSystemId());
        assertEquals("ERR_NULL", model.getCode());
        assertEquals("UserService", model.getSystemName());
        assertEquals("Additional metadata", model.getMeta());
        assertEquals("Null pointer in method X", model.getDetail());
        assertEquals("2024-08-24T10:00:00Z", model.getTime());
        assertEquals("A null reference error occurred", model.getMessage());
    }

    @Test
    void testMapObjetErreurFromDtoNull() {
        ObjetErreur model = objetErreurMapper.toModel(null);
        assertNull(model);
    }

    @Test
    void testMapObjetErreurListFromDto() {
        ObjetErreurDto dtoError1 = new ObjetErreurDto();
        dtoError1.setCode("ERR001");
        dtoError1.setMessage("Error 1");

        ObjetErreurDto dtoError2 = new ObjetErreurDto();
        dtoError2.setCode("ERR002");
        dtoError2.setMessage("Error 2");

        List<ObjetErreurDto> dtoErrors = Arrays.asList(dtoError1, dtoError2);

        List<ObjetErreur> models = objetErreurMapper.toModelList(dtoErrors);

        assertNotNull(models);
        assertEquals(2, models.size());
        assertEquals("ERR001", models.get(0).getCode());
        assertEquals("ERR002", models.get(1).getCode());
    }

    @Test
    void testMapObjetErreurListNull() {
        List<ObjetErreur> models = objetErreurMapper.toModelList(null);
        assertNull(models);
    }

    @Test
    void testMapObjetErreurListEmpty() {
        List<ObjetErreurDto> dtoErrors = List.of();
        List<ObjetErreur> models = objetErreurMapper.toModelList(dtoErrors);

        assertNotNull(models);
        assertTrue(models.isEmpty());
    }
}
