package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.dto.AuthReponseDto;
import com.awa.centrale.gestioncompte.dto.ConnectionRequeteDto;
import com.awa.centrale.gestioncompte.dto.CreationUtilisateurRequeteDto;
import com.awa.centrale.gestioncompte.dto.ModifierUtilisateurRequeteDto;
import com.awa.centrale.gestioncompte.dto.UtilisateurDto;
import com.awa.centrale.gestioncompte.mapper.AuthReponseMapper;
import com.awa.centrale.gestioncompte.mapper.ConnectionRequeteMapper;
import com.awa.centrale.gestioncompte.mapper.CreationUtilisateurRequeteMapper;
import com.awa.centrale.gestioncompte.mapper.UtilisateurMapper;
import com.awa.centrale.gestioncompte.model.AuthReponse;
import com.awa.centrale.gestioncompte.model.CreationUtilisateurRequete;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import com.awa.centrale.gestioncompte.service.utilisateur.ConnectionUtilisateurService;
import com.awa.centrale.gestioncompte.service.utilisateur.CreerUtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GestionUtilisateursController {

    private final ConnectionRequeteMapper connectionRequeteMapper;
    private final AuthReponseMapper authReponseMapper;
    private final UtilisateurMapper utilisateurMapper;
    private final CreationUtilisateurRequeteMapper creationUtilisateurRequeteMapper;

    private final ConnectionUtilisateurService connectionUtilisateurService;
    private final CreerUtilisateurService creerUtilisateurService;

    @PostMapping("/auth/login")
    public AuthReponseDto connecterUtilisateur(@RequestBody ConnectionRequeteDto connectionRequete) throws Exception {
        AuthReponse connectionReponse = connectionUtilisateurService.genererToken(connectionRequeteMapper.toModel(connectionRequete));

        return authReponseMapper.toDto(connectionReponse);
    }

    @PostMapping("/auth/register/usager")
    public UtilisateurDto creerUtilisateur(@RequestBody CreationUtilisateurRequeteDto creationUtilisateurRequeteDto) {
        CreationUtilisateurRequete creationUtilisateurRequete = creationUtilisateurRequeteMapper.toModel(creationUtilisateurRequeteDto);
        Utilisateur utilisateur = creerUtilisateurService.creerUtilisateur(creationUtilisateurRequete);
        return  utilisateurMapper.toDto(utilisateur);
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