package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.UtilisateurDao;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupprimerUtilisateurServiceTest {

    @Mock
    private UtilisateurDao utilisateurDao;

    @InjectMocks
    private SupprimerUtilisateurService supprimerUtilisateurService;

    @Test
    void supprimerUtilisateur_shouldDeactivateExistingUser() {
        int userId = 42;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(userId);
        utilisateur.setEmail("user@example.com");
        utilisateur.setActive(true);

        when(utilisateurDao.obtenirUtilisateurParId(userId)).thenReturn(utilisateur);

        supprimerUtilisateurService.supprimerUtilisateur(userId);

        ArgumentCaptor<Utilisateur> captor = ArgumentCaptor.forClass(Utilisateur.class);
        verify(utilisateurDao).sauvegarderUtilisateur(captor.capture());
        
        Utilisateur savedUser = captor.getValue();
        assertEquals(false, savedUser.getActive());
        assertEquals(userId, savedUser.getId());
        verify(utilisateurDao).obtenirUtilisateurParId(userId);
    }

    @Test
    void supprimerUtilisateur_shouldThrowExceptionWhenUserNotFound() {
        int userId = 999;
        
        when(utilisateurDao.obtenirUtilisateurParId(userId)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> supprimerUtilisateurService.supprimerUtilisateur(userId)
        );

        assertEquals("Utilisateur non trouvé avec l'ID: " + userId, exception.getMessage());
    }

    @Test
    void supprimerUtilisateur_shouldNotSaveWhenUserNotFound() {
        int userId = 100;
        
        when(utilisateurDao.obtenirUtilisateurParId(userId)).thenReturn(null);

        assertThrows(
            EntityNotFoundException.class,
            () -> supprimerUtilisateurService.supprimerUtilisateur(userId)
        );
    }
}
