package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.model.CreationRoleRequete;
import com.awa.centrale.gestioncompte.model.Role;
import org.springframework.web.bind.annotation.*;

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
