package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.AccesManager;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechercheUtilisateurService {
    @Autowired
    private AccesManager accesManager;

    public Utilisateur obtenirUtilisateur(int id) {
        Utilisateur utilisateur = accesManager.obtenirUtilisateurParId(id);
        if (utilisateur != null) {
            return utilisateur;
        } else {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id);
        }
    }
}
