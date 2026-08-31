package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.dto.CreationRoleRequeteDto;
import com.awa.centrale.gestioncompte.dto.RoleDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class GestionRolesController {
    @GetMapping
    public Iterable<RoleDto> listerRoles(){
        return null;
    }
    @PostMapping
    public RoleDto creerRole(@RequestBody CreationRoleRequeteDto role){
        return null;
    }
}
