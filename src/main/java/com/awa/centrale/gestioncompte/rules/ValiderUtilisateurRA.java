package com.awa.centrale.gestioncompte.rules;

import com.awa.centrale.gestioncompte.dao.UtilisateurDao;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import java.util.HashSet;
import java.util.Set;

public class ValiderUtilisateurRA {

    public static Set<Role> obtenirNouveauxRoles(Utilisateur utilisateur, UtilisateurDao utilisateurDao) {
        Utilisateur utilisateurExistant = utilisateurDao.obtenirUtilisateurParEmail(utilisateur.getEmail());

        if (utilisateurExistant == null) {
            return utilisateur.getRoles() == null ? Set.of() : new HashSet<>(utilisateur.getRoles());
        }

        Set<Role> rolesExistants = utilisateurExistant.getRoles() == null
                ? Set.of()
                : new HashSet<>(utilisateurExistant.getRoles());

        Set<Role> rolesDemandes = utilisateur.getRoles() == null
                ? Set.of()
                : new HashSet<>(utilisateur.getRoles());

        Set<Role> nouveauxRoles = new HashSet<>(rolesDemandes);
        nouveauxRoles.addAll(rolesExistants);

        if (nouveauxRoles.equals(rolesExistants)) {
            throw new IllegalArgumentException(
                    "Un utilisateur existant possède déjà un rôle identique à celui que vous tentez de créer."
            );
        }

        return nouveauxRoles;
    }
}