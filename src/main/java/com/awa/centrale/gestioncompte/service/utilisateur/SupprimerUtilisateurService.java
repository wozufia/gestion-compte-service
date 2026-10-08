package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.AccesManager;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupprimerUtilisateurService {

    @Autowired
    AccesManager accesManager;

    public void supprimerUtilisateur(int id) {
        Utilisateur utilisateur = accesManager.obtenirUtilisateurParId(id);
        if (utilisateur != null) {
            utilisateur.setActive(false);
            accesManager.sauvegarderUtilisateur(utilisateur);
        } else {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id);
        }
    }
}
