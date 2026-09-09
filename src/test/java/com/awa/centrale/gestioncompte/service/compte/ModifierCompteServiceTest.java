package com.awa.centrale.gestioncompte.service.compte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.Contact;
import com.awa.centrale.gestioncompte.model.ModifierCompteRequete;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ModifierCompteServiceTest {

    @Mock
    private GestionAccesRepository gestionAccesRepository;

    @InjectMocks
    private ModifierCompteService modifierCompteService;

    @Test
    void modifierCompte_shouldUpdateExistingAccount() {
        Compte compte = new Compte();
        compte.setId(8);
        compte.setNom("Ancien nom");
        compte.setContact(new Contact());

        ModifierCompteRequete requete = new ModifierCompteRequete();
        requete.setNom("Nouveau nom");
        requete.setContact(new Contact());

        when(gestionAccesRepository.obtenirCompteParId(8)).thenReturn(compte);

        Compte result = modifierCompteService.modifierCompte(8, requete);

        assertNotNull(result);
        assertEquals("Nouveau nom", result.getNom());
        verify(gestionAccesRepository).sauverCompte(compte);
    }

    @Test
    void modifierCompte_shouldFailWhenAccountDoesNotExist() {
        when(gestionAccesRepository.obtenirCompteParId(99)).thenReturn(null);

        ModifierCompteRequete requete = new ModifierCompteRequete();
        requete.setNom("Nom indisponible");

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> modifierCompteService.modifierCompte(99, requete));

        assertEquals("Compte non trouvé!", exception.getMessage());
    }
}
