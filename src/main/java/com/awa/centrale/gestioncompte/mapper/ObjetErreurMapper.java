package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.dto.ObjetErreurDto;
import com.awa.centrale.gestioncompte.model.ObjetErreur;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjetErreurMapper {

    ObjetErreur toModel(ObjetErreurDto dto);

    List<ObjetErreur> toModelList(List<ObjetErreurDto> dtoObjetErreurs);
}
