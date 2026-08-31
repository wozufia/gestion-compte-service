package com.awa.centrale.gestioncompte.service.utilisateur;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.persistence.EntityNotFoundException;

import com.awa.centrale.gestioncompte.dao.UtilisateurDao;
import com.awa.centrale.gestioncompte.model.AuthReponse;
import com.awa.centrale.gestioncompte.model.ConnectionRequete;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ConnectionUtilisateurServiceTest {

    @Mock
    private UtilisateurDao utilisateurDao;

    @InjectMocks
    private ConnectionUtilisateurService connectionUtilisateurService;

    @BeforeEach
    void setUp() {
        String jwtSecret = Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
        ReflectionTestUtils.setField(connectionUtilisateurService, "jwtSecret", jwtSecret);
    }

    @Test
    void testGenererTokenRetourneAuthReponse() throws Exception {
        ConnectionRequete detailConnection = new ConnectionRequete();
        detailConnection.setEmail("admin@gestion-compte.local");
        detailConnection.setMotDePasse("admin123");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("admin@gestion-compte.local");
        utilisateur.setMotDePasse("admin123");
        utilisateur.setFirstName("Admin");
        utilisateur.setLastName("Systeme");
        utilisateur.setRoles(Set.of());

        when(utilisateurDao.obtenirUtilisateurParEmailEtMotDePasse(
                "admin@gestion-compte.local",
                "admin123"
        )).thenReturn(utilisateur);

        AuthReponse response = connectionUtilisateurService.genererToken(detailConnection);

        assertNotNull(response);
        assertNotNull(response.getJwt());
        verify(utilisateurDao).obtenirUtilisateurParEmailEtMotDePasse(
                "admin@gestion-compte.local",
                "admin123"
        );
    }

    @Test
    void testGenererTokenThrowsWhenUtilisateurNotFound() {
        ConnectionRequete detailConnection = new ConnectionRequete();
        detailConnection.setEmail("inconnu@gestion-compte.local");
        detailConnection.setMotDePasse("mauvaisMotDePasse");

        when(utilisateurDao.obtenirUtilisateurParEmailEtMotDePasse(
                "inconnu@gestion-compte.local",
                "mauvaisMotDePasse"
        )).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> connectionUtilisateurService.genererToken(detailConnection));

        verify(utilisateurDao).obtenirUtilisateurParEmailEtMotDePasse(
                "inconnu@gestion-compte.local",
                "mauvaisMotDePasse"
        );
    }
}

