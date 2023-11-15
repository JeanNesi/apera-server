package com.apera.aperaserver.model;

import javax.persistence.*;
@Entity
@Table(name = "carteira")

public class Carteira extends EntityId {
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra historicoCompra;
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda historicoVenda;
    @ManyToOne
    @JoinColumn(name = "ativo_id")
    private Ativo ativos;

    @Column(name = "valor_total")
    private Double valorTotal;

    public Compra getHistoricoCompra() {
        return historicoCompra;
    }

    public void setHistoricoCompra(Compra historicoCompra) {
        this.historicoCompra = historicoCompra;
    }

    public Venda getHistoricoVenda() {
        return historicoVenda;
    }

    public void setHistoricoVenda(Venda historicoVenda) {
        this.historicoVenda = historicoVenda;
    }

    public Ativo getAtivos() {
        return ativos;
    }

    public void setAtivos(Ativo ativos) {
        this.ativos = ativos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
