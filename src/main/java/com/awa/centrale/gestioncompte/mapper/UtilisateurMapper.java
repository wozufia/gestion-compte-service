package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CompteMapper.class)
public interface UtilisateurMapper {

    @Mapping(target = "id", ignore = true)
    Utilisateur toModel(com.awa.centrale.gestioncompte.dto.Utilisateur dto);
}
