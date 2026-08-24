package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.dto.CreationUtilisateurRequeteDto;
import com.awa.centrale.gestioncompte.dto.ModifierUtilisateurRequeteDto;
import com.awa.centrale.gestioncompte.dto.UtilisateurDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestionUtilisateursController {
    @PostMapping("/auth/login")
    public String login(@RequestBody UtilisateurDto utilisateur) {
        // Logique pour authentifier l'utilisateur
        return "Token d'authentification";
    }

    @PostMapping("/auth/register/usager")
    public UtilisateurDto creerUtilisateur(@RequestBody CreationUtilisateurRequeteDto utilisateur) {
        // Logique pour enregistrer l'utilisateur
        return new UtilisateurDto();
    }

    @GetMapping("/usagers/{id}")
    public UtilisateurDto rechercherUtilisateur(@PathVariable int id) {
        // Logique pour rechercher un utilisateur par ID
        return null;
    }

    @PatchMapping("/usagers/{id}")
    public UtilisateurDto modifierUtilisateur(@PathVariable int id, @RequestBody ModifierUtilisateurRequeteDto ModifierUtilisateurRequete) {
        // Logique pour modifier un utilisateur
        return null;
    }

    @DeleteMapping("/usagers/{id}")
    public void supprimerUtilisateur(@PathVariable int id) {
        // Logique pour supprimer un utilisateur
    }

}