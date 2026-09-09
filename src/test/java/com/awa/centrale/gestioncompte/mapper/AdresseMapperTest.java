package com.awa.centrale.gestioncompte.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.awa.centrale.gestioncompte.dto.AdresseDto;
import com.awa.centrale.gestioncompte.model.Adresse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdresseMapperTest {
    @Autowired
    private AdresseMapper adresseMapper;

    @Test
    void testMapAdresseFromDto() {
        AdresseDto dto = new AdresseDto();
        dto.setRue("123 Rue de la Paix");
        dto.setVille("Paris");
        dto.setCodePostal("75001");

        Adresse model = adresseMapper.toModel(dto);

        assertEquals("123 Rue de la Paix", model.getRue());
        assertEquals("Paris", model.getVille());
        assertEquals("75001", model.getCodePostal());
    }

    @Test
    void testMapAdresseFromDtoNull() {
        Adresse model = adresseMapper.toModel(null);
        assertNull(model);
    }

    @Test
    void testMapAdresseWithPartialFields() {
        AdresseDto dto = new AdresseDto();
        dto.setRue("456 Avenue de la République");
        dto.setVille(null);
        dto.setCodePostal("13000");

        Adresse model = adresseMapper.toModel(dto);

        assertEquals("456 Avenue de la République", model.getRue());
        assertNull(model.getVille());
        assertEquals("13000", model.getCodePostal());
    }
}
