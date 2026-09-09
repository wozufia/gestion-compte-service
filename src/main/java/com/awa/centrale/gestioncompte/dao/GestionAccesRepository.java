package com.awa.centrale.gestioncompte.dao;

import com.awa.centrale.gestioncompte.model.Compte;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import com.awa.centrale.gestioncompte.utils.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@Transactional
public class GestionAccesRepository {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final CompteRepository compteRepository;

    public Utilisateur obtenirUtilisateurParEmailEtMotDePasse(String email, String motDePasse) {
        return utilisateurRepository.findByEmailAndMotDePasseAndActiveTrue(email, motDePasse);
    }

    public Utilisateur obtenirUtilisateurParEmail(String email) {
        return utilisateurRepository.findByEmailAndActiveTrue(email);
    }

    public Utilisateur obtenirUtilisateurParId(int id) {
        return utilisateurRepository.findByIdAndActiveTrue(id);
    }

    public Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur) {
        if (utilisateur.getRoles() != null) {
            sauverRolesSiNonExistant(utilisateur.getRoles());
        }
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur ajouterRolesAUtilisateurEtActiver(String email, Set<Role> roles) {
        Utilisateur utilisateur = utilisateurRepository.findByEmailAndActiveTrue(email);
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
            utilisateur.setEmail(email);
        }

        if (roles != null) {
            sauverRolesSiNonExistant(roles);
        }

        utilisateur.setRoles(roles);
        utilisateur.setActive(true);
        return utilisateurRepository.save(utilisateur);
    }

    public List<Role> listerRoles() {
        return roleRepository.findAll();
    }

    public Role sauverRole(Role role) {
        Role roleExistant = roleRepository.findByNom(role.getNom());
        if (roleExistant == null) {
            return roleRepository.save(role);
        }
        return role;
    }

    private void sauverRolesSiNonExistant(Set<Role> roles) {
        for (Role roleCandidat : roles) {
            String nomRole = roleCandidat.getNom();
            Role role = roleRepository.findByNom(nomRole);
            if (role == null) {
                roleRepository.save(roleCandidat);
            }
        }
    }

    public Compte obtenirCompteParNom(String nomCompte) {
       return  compteRepository.findByNom(nomCompte);
    }

    public Compte creerCompte(Compte compte) {
        Compte compteExistant = compteRepository.findByNom(compte.getNom());
        if (compteExistant == null) {
            return compteRepository.save(compte);
        }
        else{
            throw new IllegalArgumentException("Le compte avec le nom " + compte.getNom() + " existe déjà.");
        }
    }

    public Compte obtenirCompteParId(int id) {
        return compteRepository.findById(id).orElse(null);
    }

    public Compte sauverCompte(Compte compte) {
        return compteRepository.save(compte);
    }


}
