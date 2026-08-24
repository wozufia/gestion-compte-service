package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.CreationRoleRequete;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreationRoleRequeteMapper {

    CreationRoleRequete toModel(com.awa.centrale.gestioncompte.dto.CreationRoleRequete dto);
}
