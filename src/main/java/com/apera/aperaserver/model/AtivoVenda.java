package org.example;

public class AtivoVenda extends EntityId {
    private Double valorVenda;
    private Ativo ativoVenda;
    private Double quantidade;
    private String descricaoVenda;

    public Double getValorVenda() {
        return valorVenda;
    }
    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }
    public Ativo getAtivoVenda() {
        return ativoVenda;
    }
    public void setAtivoVenda(Ativo ativoVenda) {
        this.ativoVenda = ativoVenda;
    }
    public Double getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    public String getDescricaoVenda() {
        return descricaoVenda;
    }
    public void setDescricaoVenda(String descricaoVenda) {
        this.descricaoVenda = descricaoVenda;
    }
}
