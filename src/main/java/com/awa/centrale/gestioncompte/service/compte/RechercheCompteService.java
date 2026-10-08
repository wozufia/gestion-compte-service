package com.awa.centrale.gestioncompte.service.compte;

import com.awa.centrale.gestioncompte.dao.AccesManager;
import com.awa.centrale.gestioncompte.model.Compte;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechercheCompteService {

    @Autowired
    private AccesManager accesManager;

    public Compte obtenirComptePar(int id) {
        Compte compte = accesManager.obtenirCompteParId(id);
        if (compte != null) {
            return compte;
        } else {
            throw new EntityNotFoundException("Compte non trouvé!");
        }
    }
}

