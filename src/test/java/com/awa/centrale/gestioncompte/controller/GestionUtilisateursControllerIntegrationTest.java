package com.awa.centrale.gestioncompte.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.awa.centrale.gestioncompte.dto.AuthReponseDto;
import com.awa.centrale.gestioncompte.dto.ConnectionRequeteDto;
import com.awa.centrale.gestioncompte.dto.CreationUtilisateurRequeteDto;
import com.awa.centrale.gestioncompte.dto.UtilisateurDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class GestionUtilisateursControllerIntegrationTest {

    @Autowired
    private GestionUtilisateursController gestionUtilisateursController;

    @Test
    void connecterUtilisateur_retourneJwtValide() throws Exception {
        ConnectionRequeteDto requete = new ConnectionRequeteDto();
        requete.setEmail("test@gestion-compte.com");
        requete.setMotDePasse("passWord123");

        AuthReponseDto reponse = gestionUtilisateursController.connecterUtilisateur(requete);

        assertNotNull(reponse);
        assertEquals("Bearer", reponse.getTokenType());
        assertNotNull(reponse.getJwt());
        assertFalse(reponse.getJwt().isBlank());
    }

    @Test
    void creerUtilisateur_retourneUtilisateurCree() throws Exception {
        String email = "integration-user-" + System.currentTimeMillis() + "@gestion-compte.com";

        CreationUtilisateurRequeteDto requete = new CreationUtilisateurRequeteDto();
        requete.setEmail(email);
        requete.setFirstName("Integration");
        requete.setLastName("User");
        requete.setMotDePasse("SecurePass123");
        requete.setRoles("USAGER_API1");

        UtilisateurDto reponse = gestionUtilisateursController.creerUtilisateur(requete);

        assertNotNull(reponse);
        assertEquals(email, reponse.getEmail());
        assertEquals("Integration", reponse.getFirstName());
        assertEquals("User", reponse.getLastName());
        assertNotNull(reponse.getRoles());
    }
}
