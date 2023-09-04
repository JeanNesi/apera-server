package com.apera.aperaserver.model;

import java.time.LocalDate;

public class Lancamento extends EntityId {
    private Ativo ativo;
    private Integer quantidade;
    private Double custoExtra;
    private Double preco;
    private TipoAtivo tipoAtivo;
    private Pessoa pessoa;

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
