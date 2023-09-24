package com.apera.aperaserver.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda extends EntityId implements OperacaoFinanceira{
    private LocalDate dataVenda;
    private String observacao;
    private Lancamento lancamento;
    private List<AtivoVenda> ativos = new ArrayList<>();

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

    public List<AtivoVenda> getAtivos() {
        return ativos;
    }
    public void addItemVenda(AtivoVenda ativo) {
        this.ativos.add(ativo);
    }
    public void delItemVenda(AtivoVenda ativo) {
        this.ativos.remove(ativo);
    }

    @Override
    public LocalDate getDataOperacao() {
        return this.getDataVenda();
    }

    @Override
    public Double getValorTotalOperacao() {
        return this.getAtivos().stream().mapToDouble(AtivoVenda::getValorCalculado).sum();
    }

    @Override
    public TipoLancamento getTipoOperacao() {
        return TipoLancamento.VENDA;
    }
}
