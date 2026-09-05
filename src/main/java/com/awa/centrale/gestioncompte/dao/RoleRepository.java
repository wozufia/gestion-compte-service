package com.awa.centrale.gestioncompte.dao;

import com.awa.centrale.gestioncompte.model.Role;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNom(String nom);

    @Override
    @NullMarked
    List<Role> findAll();
}
