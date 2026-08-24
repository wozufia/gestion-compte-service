package com.awa.centrale.gestioncompte.model;

import lombok.Data;

@Data
public class CreationUtilisateurRequete {

  private String email;

  private String firstName;

  private String lastName;

  private String motDePasse;

}

