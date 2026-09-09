package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.model.ModifierUtilisateurRequete;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifierUtilisateurService {
    @Autowired
    private GestionAccesRepository gestionAccesRepository;

    public Utilisateur modifierUtilisateur(int id, ModifierUtilisateurRequete modifierUtilisateurRequete) {
        Utilisateur utilisateur = gestionAccesRepository.obtenirUtilisateurParId(id);

        if (utilisateur != null) {
            utilisateur.setFirstName(modifierUtilisateurRequete.getFirstName());
            utilisateur.setLastName(modifierUtilisateurRequete.getLastName());
            utilisateur.setMotDePasse(modifierUtilisateurRequete.getMotDePasse());
            return gestionAccesRepository.sauvegarderUtilisateur(utilisateur);
        } else {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID: " + id);
        }
    }
}
