package com.awa.centrale.gestioncompte.service.compte;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechercheCompteService {

    @Autowired
    private GestionAccesRepository gestionAccesRepository;

    public Compte obtenirComptePar(int id) {
        Compte compte = gestionAccesRepository.obtenirCompteParId(id);
        if (compte != null) {
            return compte;
        } else {
            throw new EntityNotFoundException("Compte non trouvé!");
        }
    }
}

