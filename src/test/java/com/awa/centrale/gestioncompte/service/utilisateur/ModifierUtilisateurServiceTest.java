package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.UtilisateurDao;
import com.awa.centrale.gestioncompte.model.ModifierUtilisateurRequete;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModifierUtilisateurServiceTest {

    @Mock
    private UtilisateurDao utilisateurDao;

    @InjectMocks
    private ModifierUtilisateurService modifierUtilisateurService;

    @Test
    void modifierUtilisateur_shouldUpdateExistingUserAndSave() {
        int userId = 15;
        ModifierUtilisateurRequete requete = new ModifierUtilisateurRequete();
        requete.setFirstName("Nouveau");
        requete.setLastName("Nom");
        requete.setMotDePasse("NouveauMotDePasse123");

        Utilisateur existingUser = new Utilisateur();
        existingUser.setId(userId);
        existingUser.setEmail("user@example.com");
        existingUser.setFirstName("Ancien");
        existingUser.setLastName("Nom");
        existingUser.setMotDePasse("AncienMotDePasse");

        Utilisateur updatedUser = new Utilisateur();
        updatedUser.setId(userId);
        updatedUser.setEmail("user@example.com");
        updatedUser.setFirstName("Nouveau");
        updatedUser.setLastName("Nom");
        updatedUser.setMotDePasse("NouveauMotDePasse123");

        when(utilisateurDao.obtenirUtilisateurParId(userId)).thenReturn(existingUser);
        when(utilisateurDao.SauvegarderUtilisateur(any(Utilisateur.class))).thenReturn(updatedUser);

        Utilisateur result = modifierUtilisateurService.modifierUtilisateur(userId, requete);

        assertNotNull(result);
        assertEquals("Nouveau", result.getFirstName());
        assertEquals("Nom", result.getLastName());
        assertEquals("NouveauMotDePasse123", result.getMotDePasse());
        verify(utilisateurDao).obtenirUtilisateurParId(userId);
        verify(utilisateurDao).SauvegarderUtilisateur(existingUser);
    }

    @Test
    void modifierUtilisateur_shouldThrowWhenUserDoesNotExist() {
        int userId = 99;
        ModifierUtilisateurRequete requete = new ModifierUtilisateurRequete();
        requete.setFirstName("Nouveau");
        requete.setLastName("Nom");
        requete.setMotDePasse("NouveauMotDePasse123");

        when(utilisateurDao.obtenirUtilisateurParId(userId)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> modifierUtilisateurService.modifierUtilisateur(userId, requete)
        );

        assertEquals("Utilisateur non trouvé avec l'ID: " + userId, exception.getMessage());
        verify(utilisateurDao).obtenirUtilisateurParId(userId);
    }
}
