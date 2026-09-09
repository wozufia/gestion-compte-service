package com.awa.centrale.gestioncompte.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dto.CompteDto;
import com.awa.centrale.gestioncompte.dto.CreationCompteRequeteDto;
import com.awa.centrale.gestioncompte.dto.ModifierCompteRequeteDto;
import com.awa.centrale.gestioncompte.mapper.CompteMapper;
import com.awa.centrale.gestioncompte.mapper.CreationCompteRequeteMapper;
import com.awa.centrale.gestioncompte.mapper.ModifierCompteRequeteMapper;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.CreationCompteRequete;
import com.awa.centrale.gestioncompte.model.ModifierCompteRequete;
import com.awa.centrale.gestioncompte.service.compte.CreerCompteService;
import com.awa.centrale.gestioncompte.service.compte.ModifierCompteService;
import com.awa.centrale.gestioncompte.service.compte.RechercheCompteService;
import com.awa.centrale.gestioncompte.service.compte.SupprimerCompteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GestionComptesControllerTest {

    @Mock
    private CreationCompteRequeteMapper creationCompteRequeteMapper;

    @Mock
    private CompteMapper compteMapper;

    @Mock
    private ModifierCompteRequeteMapper modifierCompteRequeteMapper;

    @Mock
    private CreerCompteService creerCompteService;

    @Mock
    private RechercheCompteService rechercheCompteService;

    @Mock
    private ModifierCompteService modifierCompteService;

    @Mock
    private SupprimerCompteService supprimerCompteService;

    @InjectMocks
    private GestionComptesController gestionComptesController;

    @Test
    void creerCompte_shouldMapRequestAndReturnMappedAccount() {
        CreationCompteRequeteDto dto = new CreationCompteRequeteDto();
        dto.setNom("Compte demo");

        CreationCompteRequete model = new CreationCompteRequete();
        model.setNom("Compte demo");

        Compte compte = new Compte();
        compte.setId(10);
        compte.setNom("Compte demo");

        CompteDto expected = new CompteDto();
        expected.setId(10);
        expected.setNom("Compte demo");

        when(creationCompteRequeteMapper.toModel(dto)).thenReturn(model);
        when(creerCompteService.creerCompte(model)).thenReturn(compte);
        when(compteMapper.toDto(compte)).thenReturn(expected);

        CompteDto result = gestionComptesController.creerCompte(dto);

        assertNotNull(result);
        assertEquals("Compte demo", result.getNom());
        verify(creationCompteRequeteMapper).toModel(dto);
        verify(creerCompteService).creerCompte(model);
        verify(compteMapper).toDto(compte);
    }

    @Test
    void rechercherCompte_shouldReturnMappedAccount() {
        Compte compte = new Compte();
        compte.setId(4);
        compte.setNom("Compte recherche");

        CompteDto expected = new CompteDto();
        expected.setId(4);
        expected.setNom("Compte recherche");

        when(rechercheCompteService.obtenirComptePar(4)).thenReturn(compte);
        when(compteMapper.toDto(compte)).thenReturn(expected);

        CompteDto result = gestionComptesController.rechercherCompte(4);

        assertNotNull(result);
        assertEquals(4, result.getId());
        assertEquals("Compte recherche", result.getNom());
        verify(rechercheCompteService).obtenirComptePar(4);
        verify(compteMapper).toDto(compte);
    }

    @Test
    void modifierCompte_shouldMapRequestAndReturnUpdatedAccount() {
        ModifierCompteRequeteDto dto = new ModifierCompteRequeteDto();
        dto.setNom("Compte modifie");

        ModifierCompteRequete model = new ModifierCompteRequete();
        model.setNom("Compte modifie");

        Compte compte = new Compte();
        compte.setId(7);
        compte.setNom("Compte modifie");

        CompteDto expected = new CompteDto();
        expected.setId(7);
        expected.setNom("Compte modifie");

        when(modifierCompteRequeteMapper.toModel(dto)).thenReturn(model);
        when(modifierCompteService.modifierCompte(7, model)).thenReturn(compte);
        when(compteMapper.toDto(compte)).thenReturn(expected);

        CompteDto result = gestionComptesController.modifierCompte(7, dto);

        assertNotNull(result);
        assertEquals("Compte modifie", result.getNom());
        verify(modifierCompteRequeteMapper).toModel(dto);
        verify(modifierCompteService).modifierCompte(7, model);
        verify(compteMapper).toDto(compte);
    }

    @Test
    void supprimerCompte_shouldDeleteAccount() {
        gestionComptesController.supprimerCompte(12);

        verify(supprimerCompteService).supprimerCompte(12);
    }
}
