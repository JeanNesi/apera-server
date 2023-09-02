package com.apera.aperaserver.model;

public class AtivoCompra extends EntityId {
    private Ativo ativoCompra;
    private Double valorAplicado;
    private Double quantidade;
    private Double liquidez;
    private String descricao;

    public Ativo getAtivoCompra() {
        return ativoCompra;
    }
    public void setAtivoCompra(Ativo ativoCompra) {
        this.ativoCompra = ativoCompra;
    }
    public Double getValorAplicado() {
        return valorAplicado;
    }
    public void setValorAplicado(Double valorAplicado) {
        this.valorAplicado = valorAplicado;
    }
    public Double getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    public Double getLiquidez() {
        return liquidez;
    }
    public void setLiquidez(Double liquidez) {
        this.liquidez = liquidez;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
