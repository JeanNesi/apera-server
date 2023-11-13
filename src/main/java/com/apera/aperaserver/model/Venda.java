package com.apera.aperaserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class Venda extends EntityId{
    @Column(name = "data_venda", nullable = false)
    private LocalDate dataVenda;
    @Column(name = "observacao")
    private String observacao;
    @OneToOne
    @JoinColumn(name = "lancamento_id")
    private Lancamento lancamento;

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
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
