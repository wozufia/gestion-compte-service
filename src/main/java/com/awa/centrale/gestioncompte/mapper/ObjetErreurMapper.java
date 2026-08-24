package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.ObjetErreur;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjetErreurMapper {

    ObjetErreur toModel(com.awa.centrale.gestioncompte.dto.ObjetErreur dto);

    List<ObjetErreur> toModelList(List<com.awa.centrale.gestioncompte.dto.ObjetErreur> dtoObjetErreurs);
}
