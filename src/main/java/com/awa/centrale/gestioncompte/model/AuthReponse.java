package com.awa.centrale.gestioncompte.model;

import lombok.Data;

@Data
public class AuthReponse {

  private String accessToken;

  private String refreshToken;

  private String tokenType;

  private Integer expiresIn;

}

