package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.dto.CreationUtilisateurRequete;
import com.awa.centrale.gestioncompte.dto.ModifierUtilisateurRequete;
import com.awa.centrale.gestioncompte.dto.Utilisateur;
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
    public String login(@RequestBody Utilisateur utilisateur) {
        // Logique pour authentifier l'utilisateur
        return "Token d'authentification";
    }

    @PostMapping("/auth/register/usager")
    public Utilisateur creerUtilisateur(@RequestBody CreationUtilisateurRequete utilisateur) {
        // Logique pour enregistrer l'utilisateur
        return new Utilisateur();
    }

    @GetMapping("/usagers/{id}")
    public Utilisateur rechercherUtilisateur(@PathVariable int id) {
        // Logique pour rechercher un utilisateur par ID
        return null;
    }

    @PatchMapping("/usagers/{id}")
    public Utilisateur modifierUtilisateur(@PathVariable int id, @RequestBody ModifierUtilisateurRequete ModifierUtilisateurRequete) {
        // Logique pour modifier un utilisateur
        return null;
    }

    @DeleteMapping("/usagers/{id}")
    public void supprimerUtilisateur(@PathVariable int id) {
        // Logique pour supprimer un utilisateur
    }

}