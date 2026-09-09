package com.awa.centrale.gestioncompte.service.utilisateur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RechercheUtilisateurServiceTest {

    @Mock
    private GestionAccesRepository gestionAccesRepository;

    @InjectMocks
    private RechercheUtilisateurService rechercheUtilisateurService;

    @Test
    void obtenirUtilisateur_shouldDelegateToDao() {
        Utilisateur expected = new Utilisateur();
        expected.setId(12);
        expected.setEmail("service-user@example.com");

        when(gestionAccesRepository.obtenirUtilisateurParId(12)).thenReturn(expected);

        Utilisateur result = rechercheUtilisateurService.obtenirUtilisateur(12);

        assertNotNull(result);
        assertEquals(12, result.getId());
        assertEquals("service-user@example.com", result.getEmail());
        verify(gestionAccesRepository).obtenirUtilisateurParId(12);
    }
}
