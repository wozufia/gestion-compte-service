package com.awa.centrale.gestioncompte.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class TestIntegrationUtilisateurDao {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Test
    void obtenirUtilisateurParEmailEtMotDePasse_retourneUtilisateur() {
        Utilisateur utilisateur = utilisateurDao.obtenirUtilisateurParEmailEtMotDePasse("test@gestion-compte.com", "passWord123");

        assertNotNull(utilisateur);
        assertEquals("test@gestion-compte.com", utilisateur.getEmail());
        assertEquals("passWord123", utilisateur.getMotDePasse());
        assertNotNull(utilisateur.getRoles());
        assertFalse(utilisateur.getRoles().isEmpty());
        List<String> roleNames = utilisateur.getRoles().stream().map(Role::getName).toList();
        assertEquals(List.of("ADMIN", "USAGER"), roleNames);
    }
}
