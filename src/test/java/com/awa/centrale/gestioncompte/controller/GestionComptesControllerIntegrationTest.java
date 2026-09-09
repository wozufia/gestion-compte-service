package com.awa.centrale.gestioncompte.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.awa.centrale.gestioncompte.dto.CompteDto;
import com.awa.centrale.gestioncompte.dto.CreationCompteRequeteDto;
import com.awa.centrale.gestioncompte.dto.ModifierCompteRequeteDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
class GestionComptesControllerIntegrationTest {

    @Autowired
    private GestionComptesController gestionComptesController;

    @Value("${jwt.secret.key}")
    private String jwtSecret;

    @Test
    void creerEtRechercherCompte_shouldPersistAccount() {

        String nom = "Compte integration " + System.currentTimeMillis();

        CreationCompteRequeteDto requete = new CreationCompteRequeteDto();
        requete.setNom(nom);
        requete.setApplication("gestion-compte");

        CompteDto compteCree = gestionComptesController.creerCompte(requete);

        assertNotNull(compteCree);
        assertEquals(nom, compteCree.getNom());
        assertEquals("gestion-compte", compteCree.getApplication());

        CompteDto compteTrouve = gestionComptesController.rechercherCompte(compteCree.getId());
        assertNotNull(compteTrouve);
        assertEquals(compteCree.getId(), compteTrouve.getId());
        assertEquals(nom, compteTrouve.getNom());
    }

    @Test
    void modifierEtSupprimerCompte_shouldUpdateThenDeactivateAccount() {
        CreationCompteRequeteDto creation = new CreationCompteRequeteDto();
        creation.setNom("Compte a modifier " + System.currentTimeMillis());

        CompteDto compteCree = gestionComptesController.creerCompte(creation);

        ModifierCompteRequeteDto modification = new ModifierCompteRequeteDto();
        modification.setNom("Compte modifie " + System.currentTimeMillis());

        CompteDto compteModifie = gestionComptesController.modifierCompte(compteCree.getId(), modification);
        assertNotNull(compteModifie);
        assertEquals(modification.getNom(), compteModifie.getNom());

        gestionComptesController.supprimerCompte(compteCree.getId());
    }

}
