package com.awa.centrale.gestioncompte.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awa.centrale.gestioncompte.dto.UtilisateurDto;
import com.awa.centrale.gestioncompte.mapper.AuthReponseMapper;
import com.awa.centrale.gestioncompte.mapper.ConnectionRequeteMapper;
import com.awa.centrale.gestioncompte.mapper.CreationUtilisateurRequeteMapper;
import com.awa.centrale.gestioncompte.mapper.UtilisateurMapper;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import com.awa.centrale.gestioncompte.service.utilisateur.ConnectionUtilisateurService;
import com.awa.centrale.gestioncompte.service.utilisateur.CreerUtilisateurService;
import com.awa.centrale.gestioncompte.service.utilisateur.RechercheUtilisateurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GestionUtilisateursControllerTest {

    @Mock
    private ConnectionRequeteMapper connectionRequeteMapper;

    @Mock
    private AuthReponseMapper authReponseMapper;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @Mock
    private CreationUtilisateurRequeteMapper creationUtilisateurRequeteMapper;

    @Mock
    private ConnectionUtilisateurService connectionUtilisateurService;

    @Mock
    private CreerUtilisateurService creerUtilisateurService;

    @Mock
    private RechercheUtilisateurService rechercheUtilisateurService;

    @InjectMocks
    private GestionUtilisateursController gestionUtilisateursController;

    @Test
    void rechercherUtilisateur_shouldReturnMappedUser() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(42);
        utilisateur.setEmail("user@example.com");

        UtilisateurDto expectedDto = new UtilisateurDto();
        expectedDto.setId(42);
        expectedDto.setEmail("user@example.com");

        when(rechercheUtilisateurService.obtenirUtilisateur(42)).thenReturn(utilisateur);
        when(utilisateurMapper.toDto(utilisateur)).thenReturn(expectedDto);

        UtilisateurDto result = gestionUtilisateursController.rechercherUtilisateur(42);

        assertEquals(expectedDto.getEmail(), result.getEmail());
        assertEquals(expectedDto.getId(), result.getId());
        verify(rechercheUtilisateurService).obtenirUtilisateur(42);
        verify(utilisateurMapper).toDto(utilisateur);
    }
}
