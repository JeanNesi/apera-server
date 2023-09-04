package com.apera.aperaserver.model;

import java.time.LocalDate;

public class Lancamento extends EntityId {
    private Ativo ativo;
    private Double quantidade;
    private Double custoExtra;
    private Double preco;
    private TipoAtivo tipoAtivo;
    private TipoLancamento tipoLancamento;

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getCustoExtra() {
        return custoExtra;
    }

    public void setCustoExtra(Double custoExtra) {
        this.custoExtra = custoExtra;
    }


    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public TipoAtivo getTipoAtivo() {
        return tipoAtivo;
    }

    public void setTipoAtivo(TipoAtivo tipoAtivo) {
        this.tipoAtivo = tipoAtivo;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }
}
