package com.awa.centrale.gestioncompte.model;

import lombok.Data;

@Data
public class Adresse {

  private String rue;

  private String ville;

  private String province;

  private String pays;

  private String codePostal;

  private String description;
}

