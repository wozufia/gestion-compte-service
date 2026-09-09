package com.awa.centrale.gestioncompte.dao;

import com.awa.centrale.gestioncompte.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

    Compte findByNom(String nom);
}
