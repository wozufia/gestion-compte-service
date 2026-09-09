package com.awa.centrale.gestioncompte.utils;

import com.awa.centrale.gestioncompte.enums.StatusCompteEnum;
import com.awa.centrale.gestioncompte.model.Adresse;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.Contact;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Telephone;

public final class Default {
    public static final String USER_ROLE_NAME = "USAGER_PUBLIC";
    public static final String USER_ROLE_DESCRIPTION = "Utilisateur public";

    public static Role defaultRole() {
        Role role = new Role();
        role.setNom(USER_ROLE_NAME);
        role.setDescription(USER_ROLE_DESCRIPTION);
        return role;
    }

    public static Compte defaultCompte() {
        Compte compte = new Compte();
        compte.setNom("COMPTE_PUBLIQUE");
        compte.setApplication("Application publique");
        compte.setStatus(StatusCompteEnum.ACTIF);
        compte.setContact(defaultContact());
        return compte;
    }

    public static Contact defaultContact() {

        Contact contact = new Contact();
        contact.setEmail("contact@publique.com");
        contact.setTelephone(defaultTelephone());
        contact.setAdresse(defaultAdresse());
        return contact;
    }

    public static Adresse defaultAdresse() {
        Adresse adresse = new Adresse();
        adresse.setRue("Aucune rue disponible");
        adresse.setVille("Aucune ville disponible");
        adresse.setCodePostal("00000");
        return adresse;
    }

    public static Telephone defaultTelephone() {
        Telephone telephone = new Telephone();
        telephone.setIndicatif("+00");
        telephone.setNumero("00 00 00 00 00");
        return telephone;
    }
}
