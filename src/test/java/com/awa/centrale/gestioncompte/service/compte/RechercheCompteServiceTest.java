package com.awa.centrale.gestioncompte.service.compte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.model.Compte;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RechercheCompteServiceTest {

    @Mock
    private GestionAccesRepository gestionAccesRepository;

    @InjectMocks
    private RechercheCompteService rechercheCompteService;

    @Test
    void obtenirComptePar_shouldReturnExistingAccount() {
        Compte compte = new Compte();
        compte.setId(5);
        compte.setNom("Compte recherche");

        when(gestionAccesRepository.obtenirCompteParId(5)).thenReturn(compte);

        Compte result = rechercheCompteService.obtenirComptePar(5);

        assertNotNull(result);
        assertEquals(5, result.getId());
        assertEquals("Compte recherche", result.getNom());
    }

    @Test
    void obtenirComptePar_shouldThrowWhenAccountDoesNotExist() {
        when(gestionAccesRepository.obtenirCompteParId(999)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> rechercheCompteService.obtenirComptePar(999));

        assertEquals("Compte non trouvé!", exception.getMessage());
    }
}
