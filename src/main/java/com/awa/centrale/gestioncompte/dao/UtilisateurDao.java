package com.awa.centrale.gestioncompte.dao;

import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UtilisateurDao {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    public Utilisateur obtenirUtilisateurParEmailEtMotDePasse(String email, String motDePasse) {
        return utilisateurRepository.findByEmailAndMotDePasse(email, motDePasse);
    }

    public Utilisateur obtenirUtilisateurParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur obtenirUtilisateurParId(int id) {
        return utilisateurRepository.findById(id);
    }
    public Utilisateur SauvegarderUtilisateur(Utilisateur utilisateur) {
        if (utilisateur.getRoles() != null) {
            SauverRolesSiNonExistant(utilisateur.getRoles());
        }
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur ajouterRolesAUtilisateurEtActiver(String email, Set<Role> roles) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
            utilisateur.setEmail(email);
        }

        if (roles != null) {
            SauverRolesSiNonExistant(roles);
        }

        utilisateur.setRoles(roles);
        utilisateur.setActive(true);
        return utilisateurRepository.save(utilisateur);
    }

    private void SauverRolesSiNonExistant(Set<Role> roles) {
        for (Role roleCandidat : roles) {
            String nomRole = roleCandidat.getName();
            Role role = roleRepository.findByName(nomRole);
            if (role == null) {
                roleRepository.save(roleCandidat);
            }
        }
    }
}
