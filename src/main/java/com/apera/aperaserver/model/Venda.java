package org.example;

import java.util.ArrayList;
import java.util.List;

public class Venda extends EntityId {
    private List<AtivoVenda> ativo = new ArrayList<>();
    private Double valorVenda;
    private String descricao;

    public List<AtivoVenda> getAtivo() {
        return ativo;
    }
    public void setAtivo(List<AtivoVenda> ativo) {
        this.ativo = ativo;
    }
    public Double getValorVenda() {
        return valorVenda;
    }
    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
