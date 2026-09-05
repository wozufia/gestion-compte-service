package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.dto.CreationRoleRequeteDto;
import com.awa.centrale.gestioncompte.dto.RoleDto;
import com.awa.centrale.gestioncompte.mapper.CreationRoleRequeteMapper;
import com.awa.centrale.gestioncompte.mapper.RoleMapper;
import com.awa.centrale.gestioncompte.model.CreationRoleRequete;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.service.role.CreerRoleService;
import com.awa.centrale.gestioncompte.service.role.ListerRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class GestionRolesController {
    private final RoleMapper roleMapper;
    private final CreationRoleRequeteMapper creationRoleRequeteMapper;
    private final ListerRolesService listerRolesService;
    private final CreerRoleService creerRoleService;

    @GetMapping
    public List<RoleDto> listerRoles(){
        List<Role> roles = listerRolesService.listerRoles();
        return roleMapper.toDtoList(roles);
    }
    @PostMapping
    public RoleDto creerRole(@RequestBody CreationRoleRequeteDto creationRoleRequeteDto){
        CreationRoleRequete creationRoleRequete = creationRoleRequeteMapper.toModel(creationRoleRequeteDto);
        Role role = creerRoleService.creerRole(creationRoleRequete);
        return roleMapper.toDto(role);
    }
}
