package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.GestionAccesRepository;
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
    private GestionAccesRepository gestionAccesRepository;

    public Utilisateur creerUtilisateur(CreationUtilisateurRequete creationUtilisateurRequete) {
        Set<String> roles = listerRolesDeRequete(creationUtilisateurRequete.getRoles());
        Utilisateur utilisateurDemande = construireUtilisateur(creationUtilisateurRequete, roles);

        Utilisateur utilisateurExistant = gestionAccesRepository.obtenirUtilisateurParEmail(utilisateurDemande.getEmail());

        if (utilisateurExistant == null) {
            return creerNouvelUtilisateur(utilisateurDemande);
        }

        return ajouterRolesAUtilisateurExistant(utilisateurDemande, utilisateurExistant);
    }

    private Utilisateur creerNouvelUtilisateur(Utilisateur utilisateur) {

        return gestionAccesRepository.sauvegarderUtilisateur(utilisateur);
    }

    private Utilisateur ajouterRolesAUtilisateurExistant(Utilisateur utilisateur, Utilisateur utilisateurExistant) {
        Set<Role> nouveauxRoles = obtenirNouveauxRoles(utilisateur, gestionAccesRepository);

        utilisateurExistant.setRoles(nouveauxRoles);

        return gestionAccesRepository.ajouterRolesAUtilisateurEtActiver(utilisateurExistant.getEmail(),nouveauxRoles);
    }

    private Utilisateur construireUtilisateur(CreationUtilisateurRequete creationUtilisateurRequete, Set<String> roles) {
        Compte compteExistant = gestionAccesRepository.obtenirCompteParNom(creationUtilisateurRequete.getCompte());

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

        Compte compteExistant = gestionAccesRepository.obtenirCompteParNom(nomCompte);
        return compteExistant!=null?compteExistant: Default.defaultCompte();
    }

}
