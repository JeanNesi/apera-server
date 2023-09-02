package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra extends EntityId {
    private LocalDate dataCompra;
    private LocalDate dataVencimento;
    private String descricao; // Descrição sobre a compra realizada
    private List<AtivoCompra> ativo = new ArrayList<>();

    public LocalDate getDataCompra() {
        return dataCompra;
    }
    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public List<AtivoCompra> getAtivo() {
        return ativo;
    }
    public void setAtivo(List<AtivoCompra> ativo) {
        this.ativo = ativo;
    }
}
