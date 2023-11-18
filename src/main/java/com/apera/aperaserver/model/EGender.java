package com.apera.aperaserver.model;

public enum EGender {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_INFORMADO("Não informado");

    private final String formattedValue;

    EGender(String formattedValue) {
        this.formattedValue = formattedValue;
    }

    public String getFormattedValue() {
        return formattedValue;
    }
}
