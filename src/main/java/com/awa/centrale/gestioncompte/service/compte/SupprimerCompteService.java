package com.awa.centrale.gestioncompte.service.compte;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.enums.StatusCompteEnum;
import com.awa.centrale.gestioncompte.model.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupprimerCompteService {

    @Autowired
    GestionAccesRepository gestionAccesRepository;

    public void supprimerCompte(int compteId) {
        Compte compte = gestionAccesRepository.obtenirCompteParId(compteId);
        if (compte != null) {
            compte.setStatus(StatusCompteEnum.INACTIF);
            gestionAccesRepository.sauverCompte(compte);
        } else {
            throw new RuntimeException("Compte non trouvé!");
        }
    }
}
