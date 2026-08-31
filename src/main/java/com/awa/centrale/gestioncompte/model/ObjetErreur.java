package com.awa.centrale.gestioncompte.model;

import lombok.Data;

@Data
public class ObjetErreur {

  private String exception;

  private String systemId;

  private String code;

  private String systemName;

  private Object meta;

  private String detail;

  private String time;

  private String message;

}

