package com.apera.aperaserver.model;

public class AtivoVenda extends EntityId {
    private Double valorVenda;
    private Ativo ativoVenda;
    private Int quantidade;
    private Double custoExtra;

    public Double getValorVenda() {
        return valorVenda;
    }
    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }
    public Ativo getAtivoVenda() {
        return ativoVenda;
    }
    public void setAtivoVenda(Ativo ativoVenda) {
        this.ativoVenda = ativoVenda;
    }
    public Int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getCustoExtra() {
        return custoExtra;
    }
    public void setCustoExtra(Double custoExtra) {
        this.custoExtra = custoExtra;
    }
    
}
