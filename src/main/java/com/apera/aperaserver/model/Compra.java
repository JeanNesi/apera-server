package com.apera.aperaserver.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra extends EntityId implements OperacaoFinanceira{
    private LocalDate dataCompra;
    private String observacao;
    private Lancamento lancamento;
    private List<AtivoCompra> ativos = new ArrayList<>();

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

    public List<AtivoCompra> getAtivos() {
        return ativos;
    }
    public void addItemCompra(AtivoCompra ativo) {
        this.ativos.add(ativo);
    }
    public void delItemCompra(AtivoCompra ativo) {
        this.ativos.remove(ativo);
    }

    @Override
    public LocalDate getDataOperacao() {
        return this.getDataCompra();
    }
    @Override
    public Double getValorTotalOperacao() {
        return this.getAtivos().stream().mapToDouble(AtivoCompra::getValorCalculado).sum();
    }
    @Override
    public TipoLancamento getTipoOperacao() {
        return TipoLancamento.COMPRA;
    }
}
