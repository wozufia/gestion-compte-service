package com.awa.centrale.gestioncompte.model;

import com.awa.centrale.gestioncompte.enums.StatusCompteEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "compte")
public class Compte {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nom;

  private String application;

  @Enumerated(EnumType.STRING)
  private StatusCompteEnum status;

  @Transient
  private Contact contact;

}
