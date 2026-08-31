package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.dto.UtilisateurDto;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompteMapper.class, RoleMapper.class})
public interface UtilisateurMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "motDePasse", ignore = true)
    Utilisateur toModel(UtilisateurDto dto);

    UtilisateurDto toDto(Utilisateur model);
}
