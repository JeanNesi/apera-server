package com.apera.aperaserver.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "carteira")

public class Carteira extends EntityId{
    @Column(name = "usuario", nullable = false)
    private Usuario usuario;
    @Column(name = "ativos")
    private List<Ativo> ativos;
    @Column(name = "valorTotal")
    private Double valorTotal;
    @Column(name = "historicoCompras")
    private List<Compra> historicoCompras;
    @Column(name = "historicoVendas")
    private List<Venda> historicoVendas;

    @OneToOne(mappedBy = "usuario")

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Ativo> getAtivos() {
        return ativos;
    }

    public void setAtivos(List<Ativo> ativos) {
        this.ativos = ativos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Compra> getHistoricoCompras() {
        return historicoCompras;
    }

    public void setHistoricoCompras(List<Compra> historicoCompras) {
        this.historicoCompras = historicoCompras;
    }

    public List<Venda> getHistoricoVendas() {
        return historicoVendas;
    }

    public void setHistoricoVendas(List<Venda> historicoVendas) {
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
