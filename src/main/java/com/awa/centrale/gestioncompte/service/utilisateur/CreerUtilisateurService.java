package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.UtilisateurDao;
import com.awa.centrale.gestioncompte.model.CreationUtilisateurRequete;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.awa.centrale.gestioncompte.rules.GestionRolesRA.listerRolesDeRequete;
import static com.awa.centrale.gestioncompte.rules.GestionRolesRA.obtenirRolesDeRequete;
import static com.awa.centrale.gestioncompte.rules.ValiderUtilisateurRA.obtenirNouveauxRoles;

@Service
public class CreerUtilisateurService {

    @Autowired
    private UtilisateurDao utilisateurDao;

    public Utilisateur creerUtilisateur(CreationUtilisateurRequete creationUtilisateurRequete) {
        Set<String> roles = listerRolesDeRequete(creationUtilisateurRequete.getRoles());
        Utilisateur utilisateurDemande = construireUtilisateur(creationUtilisateurRequete, roles);

        Utilisateur utilisateurExistant = utilisateurDao.obtenirUtilisateurParEmail(utilisateurDemande.getEmail());

        if (utilisateurExistant == null) {
            return creerNouvelUtilisateur(utilisateurDemande);
        }

        return ajouterRolesAUtilisateurExistant(utilisateurDemande, utilisateurExistant);
    }

    private Utilisateur creerNouvelUtilisateur(Utilisateur utilisateur) {

        return utilisateurDao.SauvegarderUtilisateur(utilisateur);
    }

    private Utilisateur ajouterRolesAUtilisateurExistant(Utilisateur utilisateur, Utilisateur utilisateurExistant) {
        Set<Role> nouveauxRoles = obtenirNouveauxRoles(utilisateur, utilisateurDao);

        utilisateurExistant.setRoles(nouveauxRoles);

        return utilisateurDao.ajouterRolesAUtilisateurEtActiver(utilisateurExistant.getEmail(),nouveauxRoles);
    }

    private Utilisateur construireUtilisateur(CreationUtilisateurRequete creationUtilisateurRequete, Set<String> roles) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(creationUtilisateurRequete.getEmail());
        utilisateur.setFirstName(creationUtilisateurRequete.getFirstName());
        utilisateur.setLastName(creationUtilisateurRequete.getLastName());
        utilisateur.setMotDePasse(creationUtilisateurRequete.getMotDePasse());
        utilisateur.setRoles(obtenirRolesDeRequete(roles));
        utilisateur.setActive(true);
        return utilisateur;
    }

}
