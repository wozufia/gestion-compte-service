package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.dto.CreationRoleRequete;
import com.awa.centrale.gestioncompte.dto.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class GestionRolesController {
    @GetMapping
    public Iterable<Role> listerRoles(){
        return null;
    }
    @PostMapping
    public Role creerRole(@RequestBody CreationRoleRequete role){
        return null;
    }
}
