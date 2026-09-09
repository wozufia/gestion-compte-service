package com.awa.centrale.gestioncompte.enums;

public enum StatusCompteEnum {
    ACTIF("Actif"),
    INACTIF("Inactif"),
    SUSPENDU("Suspendu");

    private final String status;

    StatusCompteEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
