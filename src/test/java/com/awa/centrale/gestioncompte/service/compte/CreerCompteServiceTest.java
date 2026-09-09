package com.awa.centrale.gestioncompte.service.compte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.enums.StatusCompteEnum;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.Contact;
import com.awa.centrale.gestioncompte.model.CreationCompteRequete;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreerCompteServiceTest {

    @Mock
    private GestionAccesRepository gestionAccesRepository;

    @InjectMocks
    private CreerCompteService creerCompteService;

    @Test
    void creerCompte_shouldBuildAccountAndAttachItToCurrentUser() {
        CreationCompteRequete requete = new CreationCompteRequete();
        requete.setNom("Compte principal");
        requete.setContact(new Contact());

        Compte compteCree = new Compte();
        compteCree.setId(12);
        compteCree.setNom("Compte principal");
        compteCree.setApplication("gestion-compte");
        compteCree.setStatus(StatusCompteEnum.ACTIF);

        when(gestionAccesRepository.creerCompte(any(Compte.class))).thenReturn(compteCree);

        Compte result = creerCompteService.creerCompte(requete);

        assertNotNull(result);
        assertEquals("Compte principal", result.getNom());
        assertEquals("gestion-compte", result.getApplication());
        assertEquals(StatusCompteEnum.ACTIF, result.getStatus());
        verify(gestionAccesRepository).creerCompte(any(Compte.class));

    }

    @Test
    void creerCompte_shouldFailWhenRepositoryReturnsNull() {
        CreationCompteRequete requete = new CreationCompteRequete();
        requete.setNom("Compte test");

        when(gestionAccesRepository.creerCompte(any(Compte.class))).thenReturn(null);

            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> creerCompteService.creerCompte(requete));

            assertEquals("Une erreur est survenue lors de la création du compte!", exception.getMessage());

    }
}
