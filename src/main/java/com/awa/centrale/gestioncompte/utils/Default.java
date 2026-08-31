package com.awa.centrale.gestioncompte.utils;

import com.awa.centrale.gestioncompte.model.Role;

public final class Default {
    public static final String USER_ROLE_NAME = "USAGER_PUBLIC";
    public static final String USER_ROLE_DESCRIPTION = "Utilisateur public";

    private Default() {
    }

    public static Role defaultRole() {
        Role role = new Role();
        role.setName(USER_ROLE_NAME);
        role.setDescription(USER_ROLE_DESCRIPTION);
        return role;
    }
}
