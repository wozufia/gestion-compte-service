package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.dto.AdresseDto;
import com.awa.centrale.gestioncompte.model.Adresse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdresseMapper {

    Adresse toModel(AdresseDto dto);

    AdresseDto toDto(Adresse adresse);
}
