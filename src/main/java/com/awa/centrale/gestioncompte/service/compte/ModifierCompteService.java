package com.awa.centrale.gestioncompte.service.compte;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.ModifierCompteRequete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifierCompteService {
    @Autowired
    GestionAccesRepository gestionAccesRepository;
    public Compte modifierCompte(int compteiD, ModifierCompteRequete modifierCompteRequete) {
        Compte compte = gestionAccesRepository.obtenirCompteParId(compteiD);
        if (compte == null) {
            throw new RuntimeException("Compte non trouvé!");
        }
        compte.setNom(modifierCompteRequete.getNom());
        compte.setContact(modifierCompteRequete.getContact());
        gestionAccesRepository.sauverCompte( compte);
        return compte;
    }
}
