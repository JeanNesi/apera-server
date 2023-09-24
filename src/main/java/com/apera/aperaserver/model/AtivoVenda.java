package com.apera.aperaserver.model;

public class AtivoVenda extends EntityId{
    private Ativo ativo;
    private Double valorUnitario;
    private Double quantidade;

    public AtivoVenda(Ativo ativo, Double valorUnitario, Double quantidade) {
        this.ativo = ativo;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    public Double getValorCalculado() {
        double valorTotal = this.getValorUnitario() * this.getQuantidade();
        return valorTotal;
    }
}
