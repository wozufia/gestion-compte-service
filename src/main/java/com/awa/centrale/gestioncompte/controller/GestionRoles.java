package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.model.CreationRoleRequete;
import com.awa.centrale.gestioncompte.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class GestionRoles {
    @GetMapping
    public Iterable<Role> listerRoles(){
        return null;
    }
    @PostMapping
    public Role creerRole(@RequestBody CreationRoleRequete role){
        return null;
    }
}
