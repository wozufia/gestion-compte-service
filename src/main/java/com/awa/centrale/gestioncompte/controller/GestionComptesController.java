package com.awa.centrale.gestioncompte.controller;

import com.awa.centrale.gestioncompte.dto.CompteDto;
import com.awa.centrale.gestioncompte.dto.CreationCompteRequeteDto;
import com.awa.centrale.gestioncompte.dto.ModifierCompteRequeteDto;
import com.awa.centrale.gestioncompte.mapper.CompteMapper;
import com.awa.centrale.gestioncompte.mapper.CreationCompteRequeteMapper;
import com.awa.centrale.gestioncompte.mapper.ModifierCompteRequeteMapper;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.CreationCompteRequete;
import com.awa.centrale.gestioncompte.model.ModifierCompteRequete;
import com.awa.centrale.gestioncompte.service.compte.CreerCompteService;
import com.awa.centrale.gestioncompte.service.compte.ModifierCompteService;
import com.awa.centrale.gestioncompte.service.compte.RechercheCompteService;
import com.awa.centrale.gestioncompte.service.compte.SupprimerCompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GestionComptesController {
    private final CreationCompteRequeteMapper creationCompteRequeteMapper;
    private final CompteMapper compteMapper;
    private final ModifierCompteRequeteMapper modifierCompteRequeteMapper;
    private final CreerCompteService creerCompteService;
    private final RechercheCompteService rechercheCompteService;
    private final ModifierCompteService modifierCompteService;
    private final SupprimerCompteService supprimerCompteService;

    @PostMapping("/auth/register/compte")
    public CompteDto creerCompte(@RequestBody CreationCompteRequeteDto creationCompteRequeteDto) {
        CreationCompteRequete creationCompteRequete = creationCompteRequeteMapper.toModel(creationCompteRequeteDto);
        Compte compteCree = creerCompteService.creerCompte(creationCompteRequete);
        return compteMapper.toDto(compteCree);
    }

    @GetMapping("/compte/{id}")
    public CompteDto rechercherCompte(@PathVariable int id) {
        Compte compte = rechercheCompteService.obtenirComptePar(id);
        return compteMapper.toDto(compte);
    }

    @PostMapping("/compte/{id}")
    public CompteDto modifierCompte(@PathVariable int id, @RequestBody ModifierCompteRequeteDto modifierCompteRequeteDto) {
        ModifierCompteRequete modifierCompteRequete = modifierCompteRequeteMapper.toModel(modifierCompteRequeteDto);
        Compte compteModifie = modifierCompteService.modifierCompte(id, modifierCompteRequete);
        return compteMapper.toDto(compteModifie);
    }

    @DeleteMapping("/compte/{id}")
    public void supprimerCompte(@PathVariable int id) {
        supprimerCompteService.supprimerCompte(id);
    }
}
