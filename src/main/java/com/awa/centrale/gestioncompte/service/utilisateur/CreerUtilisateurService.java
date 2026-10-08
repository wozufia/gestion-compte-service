package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.AccesManager;
import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.CreationUtilisateurRequete;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import com.awa.centrale.gestioncompte.rules.GestionRolesRA;
import com.awa.centrale.gestioncompte.utils.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.awa.centrale.gestioncompte.rules.GestionRolesRA.listerRolesDeRequete;
import static com.awa.centrale.gestioncompte.rules.ValiderUtilisateurRA.obtenirNouveauxRoles;

@Service
public class CreerUtilisateurService {

    @Autowired
    private AccesManager accesManager;

    public Utilisateur creerUtilisateur(CreationUtilisateurRequete creationUtilisateurRequete) {
        Set<String> roles = listerRolesDeRequete(creationUtilisateurRequete.getRoles());
        Utilisateur utilisateurDemande = construireUtilisateur(creationUtilisateurRequete, roles);

        Utilisateur utilisateurExistant = accesManager.obtenirUtilisateurParEmail(utilisateurDemande.getEmail());

        if (utilisateurExistant == null) {
            return creerNouvelUtilisateur(utilisateurDemande);
        }

        return ajouterRolesAUtilisateurExistant(utilisateurDemande, utilisateurExistant);
    }

    private Utilisateur creerNouvelUtilisateur(Utilisateur utilisateur) {

        return accesManager.sauvegarderUtilisateur(utilisateur);
    }

    private Utilisateur ajouterRolesAUtilisateurExistant(Utilisateur utilisateur, Utilisateur utilisateurExistant) {
        Set<Role> nouveauxRoles = obtenirNouveauxRoles(utilisateur, accesManager);

        utilisateurExistant.setRoles(nouveauxRoles);

        return accesManager.ajouterRolesAUtilisateurEtActiver(utilisateurExistant.getEmail(),nouveauxRoles);
    }

    private Utilisateur construireUtilisateur(CreationUtilisateurRequete creationUtilisateurRequete, Set<String> roles) {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(creationUtilisateurRequete.getEmail());
        utilisateur.setFirstName(creationUtilisateurRequete.getFirstName());
        utilisateur.setLastName(creationUtilisateurRequete.getLastName());
        utilisateur.setMotDePasse(creationUtilisateurRequete.getMotDePasse());
        utilisateur.setRoles(GestionRolesRA.obtenirRolesDeRequete(roles));
        utilisateur.setCompte(definirCompte(creationUtilisateurRequete.getCompte()));
        utilisateur.setActive(true);
        return utilisateur;
    }

    private Compte definirCompte(String nomCompte) {

        Compte compteExistant = accesManager.obtenirCompteParNom(nomCompte);
        return compteExistant!=null?compteExistant: Default.defaultCompte();
    }

}
