package com.awa.centrale.gestioncompte.utils;

import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.Role;

public final class Default {
    public static final String USER_ROLE_NAME = "USAGER_PUBLIC";
    public static final String USER_ROLE_DESCRIPTION = "Utilisateur public";

    public static Role defaultRole() {
        Role role = new Role();
        role.setName(USER_ROLE_NAME);
        role.setDescription(USER_ROLE_DESCRIPTION);
        return role;
    }

    public static Compte defaultCompte() {
        Compte compte = new Compte();
        compte.setNom("COMPTE_PUBLIQUE");
        compte.setApplication("Application publique");
        compte.setStatus("Actif");
        return compte;
    }
}
