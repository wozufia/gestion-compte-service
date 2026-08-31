package rules;

import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.utils.Default;

import java.util.Locale;
import java.util.Set;

public class GestionRolesRA {
    public static Set<Role> obtenirRolesDeRequete(Set<String> roleNames) {
        if (roleNames == null || roleNames.isEmpty()) {
            return Set.of(Default.defaultRole());
        }

        Set<String> rolesDistincts = new java.util.HashSet<>();
        Set<Role> roleList = new java.util.HashSet<>();

        for (String roleName : roleNames) {
            if (roleName == null) {
                continue;
            }
            String normalizedRole = roleName.trim();
            if (normalizedRole.isEmpty()) {
                continue;
            }

            String roleKey = normalizedRole.toUpperCase(Locale.ROOT);
            if (!rolesDistincts.add(roleKey)) {
                continue;
            }

            Role role = new Role();
            role.setName(roleKey);
            role.setDescription(descriptionRole(roleKey));
            roleList.add(role);
        }

        return roleList.isEmpty() ? Set.of(Default.defaultRole()) : roleList;
    }

    public static Set<String> listerRolesDeRequete(String roles) {
        if (roles == null || roles.isEmpty()) {
            return Set.of();
        }

        String[] roleNames = roles.split(",");
        Set<String> rolesNormalises = new java.util.HashSet<>();

        for (String roleName : roleNames) {
            String roleNormalise = roleName.trim();
            if (roleNormalise.isEmpty()) {
                continue;
            }
            rolesNormalises.add(roleNormalise.toUpperCase(Locale.ROOT));
        }

        return rolesNormalises;
    }
    private static String descriptionRole(String roleName) {
        return roleName
                .toLowerCase(Locale.ROOT)
                .replace("_", " de l'application ");
    }
}
