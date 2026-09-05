package com.awa.centrale.gestioncompte.dao;

import com.awa.centrale.gestioncompte.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findByEmailAndMotDePasseAndActiveTrue(String email, String motDePasse);

    Utilisateur findByEmailAndActiveTrue(String email);

    Utilisateur findByIdAndActiveTrue(int id);

}
