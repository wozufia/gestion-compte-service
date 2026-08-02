package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.model.CreationUtilisateurRequete;
import com.awa.centrale.gestioncompte.model.ModifierUtilisateurRequete;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GestionUtilisateurController {
    @PostMapping("/auth/register")
    public Utilisateur creerUtilisateur(@RequestBody CreationUtilisateurRequete utilisateur) {
        // Logique pour enregistrer l'utilisateur
        return new Utilisateur();
    }

    @GetMapping("/users")
    public Iterable<Utilisateur> listerUtilisateurs() {
        // Logique pour lister les utilisateurs
        return null;
    }

    @GetMapping("/users/{id}")
    public Utilisateur rechercherUtilisateur(@PathVariable int id) {
        // Logique pour rechercher un utilisateur par ID
        return null;
    }

    @PatchMapping("/users/{id}")
    public Utilisateur modifierUtilisateur(@PathVariable int id, @RequestBody ModifierUtilisateurRequete ModifierUtilisateurRequete) {
        // Logique pour modifier un utilisateur
        return null;
    }

    //rajouter une methode de suppression logique des utilisateur
}