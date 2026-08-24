package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.ModifierCompteRequete;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ContactMapper.class)
public interface ModifierCompteRequeteMapper {

    ModifierCompteRequete toModel(com.awa.centrale.gestioncompte.dto.ModifierCompteRequete dto);
}
