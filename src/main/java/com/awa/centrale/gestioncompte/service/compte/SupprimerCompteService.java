package com.awa.centrale.gestioncompte.service.compte;

import com.awa.centrale.gestioncompte.dao.AccesManager;
import com.awa.centrale.gestioncompte.enums.StatusCompteEnum;
import com.awa.centrale.gestioncompte.model.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupprimerCompteService {

    @Autowired
    AccesManager accesManager;

    public void supprimerCompte(int compteId) {
        Compte compte = accesManager.obtenirCompteParId(compteId);
        if (compte != null) {
            compte.setStatus(StatusCompteEnum.INACTIF);
            accesManager.sauverCompte(compte);
        } else {
            throw new RuntimeException("Compte non trouvé!");
        }
    }
}
