package com.awa.centrale.gestioncompte.model;

import lombok.Data;

@Data
public class Utilisateur {

  private Integer id;

  private String email;

  private String firstName;

  private String lastName;

  private Boolean active;

  private Compte compte;

  private Boolean administrateur;

}

