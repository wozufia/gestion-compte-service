package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TelephoneMapper.class, AdresseMapper.class})
public interface ContactMapper {

    Contact toModel(com.awa.centrale.gestioncompte.dto.Contact dto);
}
