package com.awa.centrale.gestioncompte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "compte")
public class Compte {

  @Id
  private Integer id;

  private String nom;

  private String application;

  private String status;

  @Transient
  private Contact contact;

}
