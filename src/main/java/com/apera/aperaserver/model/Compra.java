package com.apera.aperaserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class Compra extends EntityId {
    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;
    @Column(name = "observacao", length = 150)
    private String observacao;
    @OneToOne
    @JoinColumn(name = "lancamento_id")
    private Lancamento lancamento;

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }
}
