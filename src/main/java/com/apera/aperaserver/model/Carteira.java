package com.apera.aperaserver.model;

import javax.persistence.*;
@Entity
@Table(name = "carteira")

public class Carteira extends EntityId{
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "ativo_id")
    private Ativo ativos;
    @Column(name = "valorTotal")
    private Double valorTotal;
    @OneToOne
    @JoinColumn(name = "compra_id")
    private Compra historicoCompras;

    @OneToOne
    @JoinColumn(name = "venda_id")
    private Venda historicoVendas;

//    @OneToOne(mappedBy = "usuario") ver se faz sentido

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Compra getHistoricoCompras() {
        return historicoCompras;
    }

    public void setHistoricoCompras(Compra historicoCompras) {
        this.historicoCompras = historicoCompras;
    }

    public Venda getHistoricoVendas() {
        return historicoVendas;
    }

    public void setHistoricoVendas(Venda historicoVendas) {
        this.historicoVendas = historicoVendas;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "usuario=" + usuario +
                ", ativos=" + ativos +
                ", valorTotal=" + valorTotal +
                ", historicoCompras=" + historicoCompras +
                ", historicoVendas=" + historicoVendas +
                '}';
    }
}
