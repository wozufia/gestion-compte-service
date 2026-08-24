package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.dto.AuthReponseDto;
import com.awa.centrale.gestioncompte.model.AuthReponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthReponseMapper {

    AuthReponse toModel(AuthReponseDto dto);
}
