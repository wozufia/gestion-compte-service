package com.awa.centrale.gestioncompte.service.compte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.enums.StatusCompteEnum;
import com.awa.centrale.gestioncompte.model.Compte;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SupprimerCompteServiceTest {

    @Mock
    private GestionAccesRepository gestionAccesRepository;

    @InjectMocks
    private SupprimerCompteService supprimerCompteService;

    @Test
    void supprimerCompte_shouldMarkAccountAsInactive() {
        Compte compte = new Compte();
        compte.setId(3);
        compte.setNom("Compte actif");
        compte.setStatus(StatusCompteEnum.ACTIF);

        when(gestionAccesRepository.obtenirCompteParId(3)).thenReturn(compte);

        supprimerCompteService.supprimerCompte(3);

        assertEquals(StatusCompteEnum.INACTIF, compte.getStatus());
        verify(gestionAccesRepository).sauverCompte(compte);
    }

    @Test
    void supprimerCompte_shouldFailWhenAccountDoesNotExist() {
        when(gestionAccesRepository.obtenirCompteParId(77)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> supprimerCompteService.supprimerCompte(77));

        assertEquals("Compte non trouvé!", exception.getMessage());
    }
}
