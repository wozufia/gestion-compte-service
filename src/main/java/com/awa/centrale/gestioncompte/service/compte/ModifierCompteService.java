package com.awa.centrale.gestioncompte.service.compte;

import com.awa.centrale.gestioncompte.dao.AccesManager;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.ModifierCompteRequete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifierCompteService {
    @Autowired
    AccesManager accesManager;
    public Compte modifierCompte(int compteiD, ModifierCompteRequete modifierCompteRequete) {
        Compte compte = accesManager.obtenirCompteParId(compteiD);
        if (compte == null) {
            throw new RuntimeException("Compte non trouvé!");
        }
        compte.setNom(modifierCompteRequete.getNom());
        compte.setContact(modifierCompteRequete.getContact());
        accesManager.sauverCompte( compte);
        return compte;
    }
}
