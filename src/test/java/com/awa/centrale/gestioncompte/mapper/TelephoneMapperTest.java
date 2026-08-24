package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.TelephoneDto;
import com.awa.centrale.gestioncompte.model.Telephone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TelephoneMapperTest {

    @Autowired
    private TelephoneMapper telephoneMapper;

    @Test
    void testMapTelephoneFromDto() {
        TelephoneDto dto = new TelephoneDto();
        dto.setIndicatif("+33");
        dto.setNumero("612345678");

        Telephone model = telephoneMapper.toModel(dto);

        assertEquals("+33", model.getIndicatif());
        assertEquals("612345678", model.getNumero());
    }

    @Test
    void testMapTelephoneFromDtoNull() {
        Telephone model = telephoneMapper.toModel(null);
        assertNull(model);
    }

    @Test
    void testMapTelephoneWithNullIndicatif() {
        TelephoneDto dto = new TelephoneDto();
        dto.setIndicatif(null);
        dto.setNumero("612345678");

        Telephone model = telephoneMapper.toModel(dto);

        assertNull(model.getIndicatif());
        assertEquals("612345678", model.getNumero());
    }
}
