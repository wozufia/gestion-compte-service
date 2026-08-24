package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.dto.CompteDto;
import com.awa.centrale.gestioncompte.dto.CreationCompteRequeteDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestionComptesController {
    @PostMapping("/auth/register/compte")
    public CompteDto creerCompte(@RequestBody CreationCompteRequeteDto compte) {
        // Logique pour enregistrer le compte
        return new CompteDto();
    }

    @GetMapping("/compte/{id}")
    public CompteDto rechercherCompte(@PathVariable int id) {
        // Logique pour rechercher un utilisateur par ID
        return null;
    }
}
