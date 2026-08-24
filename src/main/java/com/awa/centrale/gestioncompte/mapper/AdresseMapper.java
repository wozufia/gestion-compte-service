package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.Adresse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdresseMapper {

    Adresse toModel(com.awa.centrale.gestioncompte.dto.Adresse dto);
}
