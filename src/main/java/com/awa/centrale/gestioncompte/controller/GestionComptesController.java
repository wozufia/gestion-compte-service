package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.dto.Compte;
import com.awa.centrale.gestioncompte.dto.CreationCompteRequete;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestionComptesController {
    @PostMapping("/auth/register/compte")
    public Compte creerCompte(@RequestBody CreationCompteRequete compte) {
        // Logique pour enregistrer le compte
        return new Compte();
    }

    @GetMapping("/compte/{id}")
    public Compte rechercherCompte(@PathVariable int id) {
        // Logique pour rechercher un utilisateur par ID
        return null;
    }
}
