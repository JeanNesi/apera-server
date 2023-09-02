package com.apera.aperaserver.model;

public class Cliente extends Pessoa {
    private String cpf;
    private String profissao;
    private Double rendaMensal;
    private String assinaturaEletronica;
    private Double aplicacoesFinanceiras;


    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getProfissao() {
        return profissao;
    }
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
    public Double getRendaMensal() {
        return rendaMensal;
    }
    public void setRendaMensal(Double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }
    public String getAssinaturaEletronica() {
        return assinaturaEletronica;
    }
    public void setAssinaturaEletronica(String assinaturaEletronica) {
        this.assinaturaEletronica = assinaturaEletronica;
    }
    public Double getAplicacoesFinanceiras() {
        return aplicacoesFinanceiras;
    }
    public void setAplicacoesFinanceiras(Double aplicacoesFinanceiras) {
        this.aplicacoesFinanceiras = aplicacoesFinanceiras;
    }
}
