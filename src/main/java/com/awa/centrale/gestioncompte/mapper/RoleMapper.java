package com.awa.centrale.gestioncompte.mapper;

import com.awa.centrale.gestioncompte.dto.RoleDto;
import com.awa.centrale.gestioncompte.model.Role;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "utilisateurs", ignore = true)
    Role toModel(RoleDto dto);

    List<Role> toModelList(List<RoleDto> dtoRoles);

    RoleDto toDto(Role model);
    List<RoleDto> toDtoList(List<Role> roles);
}
