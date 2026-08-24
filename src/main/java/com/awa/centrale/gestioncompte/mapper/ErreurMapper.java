package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.Erreur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ErreurMapper {

    Erreur toModel(com.awa.centrale.gestioncompte.dto.Erreur dto);
}
