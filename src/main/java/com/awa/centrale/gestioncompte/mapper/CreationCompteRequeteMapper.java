package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.CreationCompteRequete;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ContactMapper.class)
public interface CreationCompteRequeteMapper {

    CreationCompteRequete toModel(com.awa.centrale.gestioncompte.dto.CreationCompteRequete dto);
}
