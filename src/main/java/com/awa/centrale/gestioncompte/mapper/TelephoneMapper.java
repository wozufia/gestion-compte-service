package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.Telephone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelephoneMapper {

    Telephone toModel(com.awa.centrale.gestioncompte.dto.Telephone dto);
}
