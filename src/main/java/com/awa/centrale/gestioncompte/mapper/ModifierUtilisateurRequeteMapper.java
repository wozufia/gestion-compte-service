package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.ModifierUtilisateurRequete;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModifierUtilisateurRequeteMapper {

    ModifierUtilisateurRequete toModel(com.awa.centrale.gestioncompte.dto.ModifierUtilisateurRequete dto);
}
