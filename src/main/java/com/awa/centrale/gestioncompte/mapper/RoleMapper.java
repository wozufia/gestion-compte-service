package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.model.Role;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    Role toModel(com.awa.centrale.gestioncompte.dto.Role dto);

    List<Role> toModelList(List<com.awa.centrale.gestioncompte.dto.Role> dtoRoles);
}
