package com.awa.centrale.gestioncompte.dao;

import com.awa.centrale.gestioncompte.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findByEmailAndMotDePasse(String email, String motDePasse);

    Utilisateur findByEmail(String email);

    Utilisateur findById(int id);

}
