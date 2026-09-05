package com.awa.centrale.gestioncompte.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.awa.centrale.gestioncompte.dto.AuthReponseDto;
import com.awa.centrale.gestioncompte.dto.ConnectionRequeteDto;
import com.awa.centrale.gestioncompte.dto.CreationUtilisateurRequeteDto;
import com.awa.centrale.gestioncompte.dto.ModifierUtilisateurRequeteDto;
import com.awa.centrale.gestioncompte.dto.UtilisateurDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
class GestionUtilisateursControllerIntegrationTest {

    @Autowired
    private GestionUtilisateursController gestionUtilisateursController;

    @Test
    void connecterUtilisateur_retourneJwtValide() {
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
    void creerUtilisateur_retourneUtilisateurCree() {
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

    @Test
    void rechercherUtilisateur_retourneUtilisateurParId() {
        UtilisateurDto utilisateurTrouve = gestionUtilisateursController.rechercherUtilisateur(1);

        assertNotNull(utilisateurTrouve);
        assertEquals(1, utilisateurTrouve.getId());
        assertEquals("test@gestion-compte.com", utilisateurTrouve.getEmail());
        assertEquals("Prenom", utilisateurTrouve.getFirstName());
        assertEquals("Nom", utilisateurTrouve.getLastName());
        assertNotNull(utilisateurTrouve.getRoles());
    }

    @Test
    void modifierUtilisateur_retourneUtilisateurModifie() {
        int userId = 1;
        
        ModifierUtilisateurRequeteDto modifierRequete = new ModifierUtilisateurRequeteDto();
        modifierRequete.setFirstName("Modified");
        modifierRequete.setLastName("Updated");

        // Valider que l'utilisateur existe avant la modification avec les valeurs originales
        UtilisateurDto utilisateurOriginal = gestionUtilisateursController.rechercherUtilisateur(userId);
        assertNotNull(utilisateurOriginal);
        assertEquals("Prenom", utilisateurOriginal.getFirstName());
        assertEquals("Nom", utilisateurOriginal.getLastName());


        // Modifier l'utilisateur et vérifier les valeurs modifiées
        UtilisateurDto utilisateurModifie = gestionUtilisateursController.modifierUtilisateur(userId, modifierRequete);
        
        assertNotNull(utilisateurModifie);
        assertEquals(userId, utilisateurModifie.getId());
        assertEquals("Modified", utilisateurModifie.getFirstName());
        assertEquals("Updated", utilisateurModifie.getLastName());
        
        // Restore original values
        ModifierUtilisateurRequeteDto restoreRequete = new ModifierUtilisateurRequeteDto();
        restoreRequete.setFirstName("Prenom");
        restoreRequete.setLastName("Nom");
        gestionUtilisateursController.modifierUtilisateur(userId, restoreRequete);
    }

    @Test
    void supprimerUtilisateur_marksUserAsInactive() {
        UtilisateurDto utilisateur = gestionUtilisateursController.rechercherUtilisateur(1);
        assertEquals(true, utilisateur.getActive());
        
        // Should not throw an exception
        gestionUtilisateursController.supprimerUtilisateur(1);
        
        // After suppression, the user can no longer be retrieved because they are inactive
        assertThrows(
            jakarta.persistence.EntityNotFoundException.class,
            () -> gestionUtilisateursController.rechercherUtilisateur(1)
        );
    }
}
