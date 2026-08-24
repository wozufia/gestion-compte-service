package com.awa.centrale.gestioncompte.model;

import lombok.Data;

@Data
public class Compte {

  private Integer id;

  private String nom;

  private String application;

  private String status;

  private Contact contact;

}

