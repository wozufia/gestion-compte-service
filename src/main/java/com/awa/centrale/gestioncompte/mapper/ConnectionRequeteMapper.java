package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.ConnectionRequete;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConnectionRequeteMapper {

    ConnectionRequete toModel(com.awa.centrale.gestioncompte.dto.ConnectionRequete dto);
}
