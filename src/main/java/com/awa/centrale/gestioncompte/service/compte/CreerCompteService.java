package com.awa.centrale.gestioncompte.service.compte;

import com.awa.centrale.gestioncompte.dao.AccesManager;
import com.awa.centrale.gestioncompte.enums.StatusCompteEnum;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.CreationCompteRequete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreerCompteService {
    @Autowired
    AccesManager accesManager;

    public Compte creerCompte(CreationCompteRequete creationCompteRequete) {
        Compte compteDemande = new Compte();
        compteDemande.setNom(creationCompteRequete.getNom());
        compteDemande.setContact(creationCompteRequete.getContact());
        compteDemande.setApplication(creationCompteRequete.getApplication());
        compteDemande.setStatus(StatusCompteEnum.ACTIF);

        Compte compteCree = accesManager.creerCompte(compteDemande);
        if (compteCree == null) {
            throw new RuntimeException("Une erreur est survenue lors de la création du compte!");
        }
        return compteCree;
    }
}
