package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.UtilisateurDao;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupprimerUtilisateurService {

    @Autowired
    UtilisateurDao utilisateurDao;

    public void supprimerUtilisateur(int id) {
        Utilisateur utilisateur = utilisateurDao.obtenirUtilisateurParId(id);
        if (utilisateur != null) {
            utilisateur.setActive(false);
            utilisateurDao.sauvegarderUtilisateur(utilisateur);
        } else {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id);
        }
    }
}
