package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.CreationUtilisateurRequete;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreationUtilisateurRequeteMapper {

    CreationUtilisateurRequete toModel(com.awa.centrale.gestioncompte.dto.CreationUtilisateurRequete dto);
}
