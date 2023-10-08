package com.apera.aperaserver.model;

import javax.persistence.*;

@Entity
public class Lancamento extends EntityId {
    @OneToOne
    @JoinColumn(name = "ativo_id", nullable = false)
    private Ativo ativo;
    @Column(name = "quantidade", length = 100, nullable = false)
    private Double quantidade;
    @Column(name = "custoExtra", length = 100)
    private Double custoExtra;
    @Column(name = "preco", length = 100, nullable = false)
    private Double preco;
    @Column(name = "tipo_ativo", length = 100, nullable = false)
    private TipoAtivo tipoAtivo;
    @Column(name = "tipo_lancamento", length = 100, nullable = false)
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
