package com.apera.aperaserver.model;

import java.time.LocalDate;

public class Ativo extends AtivoVenda {
    private String nome;
    private String razaoSocial;
    private String logo;
    private LocalDate dataValidade;
    private Double precoCompra;

    public Ativo(Ativo ativo, Double valorUnitario, Double quantidade) {
        super(ativo, valorUnitario, quantidade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }
    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }
    public Double calcularRentabilidade() {
        double lucro = (super.getValorUnitario() * super.getQuantidade()) - precoCompra;
        double rentabilidade = lucro / (super.getValorUnitario() * super.getQuantidade()) * 100;
        return rentabilidade;
    }
}
