package com.awa.centrale.gestioncompte.model;

import lombok.Data;

@Data
public class Erreur {

  private String status;

  private Integer httpStatus;

  private String message;

}

