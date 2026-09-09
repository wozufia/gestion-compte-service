package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.dto.CompteDto;
import com.awa.centrale.gestioncompte.model.Compte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ContactMapper.class)
public interface CompteMapper {

    @Mapping(target = "id", ignore = true)
    Compte toModel(CompteDto dto);

    CompteDto toDto(Compte compte);
}
