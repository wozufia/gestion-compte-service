package com.awa.centrale.gestioncompte.service.role;

import com.awa.centrale.gestioncompte.dao.AccesManager;
import com.awa.centrale.gestioncompte.model.CreationRoleRequete;
import com.awa.centrale.gestioncompte.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreerRoleService {

    @Autowired
    private AccesManager accesManager;

    public Role creerRole( CreationRoleRequete creationRoleRequete) {

        Role role = new Role();
        role.setNom(creationRoleRequete.getNom());
        role.setDescription(creationRoleRequete.getDescription());
        return accesManager.sauverRole(role);
    }
}
