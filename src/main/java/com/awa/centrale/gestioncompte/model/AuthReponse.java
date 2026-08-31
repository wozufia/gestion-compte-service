package com.awa.centrale.gestioncompte.model;

import lombok.Data;

@Data
public class AuthReponse {

  private String jwt;

  private String tokenType;

}

