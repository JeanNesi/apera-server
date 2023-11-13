package com.apera.aperaserver.resource.representation;

import com.apera.aperaserver.model.Ativo;
import com.apera.aperaserver.model.Lancamento;
import com.apera.aperaserver.model.TipoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class LancamentoDTO {
    private Long id;
    private Ativo ativo;
    private Double quantidade;
    private Double preco;
    private TipoLancamento tipoLancamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public LancamentoDTO() {
    }

    public static LancamentoDTO fromEntity(Lancamento lancamento) {
        LancamentoDTO lancamentoDTO = new LancamentoDTO();
        lancamentoDTO.setId(lancamento.getId());
        lancamentoDTO.setAtivo(lancamento.getAtivo());
        lancamentoDTO.setQuantidade(lancamento.getQuantidade());
        lancamentoDTO.setPreco(lancamento.getPreco());
        lancamentoDTO.setTipoLancamento(lancamento.getTipoLancamento());
        return lancamentoDTO;
    }

    public static List<LancamentoDTO> fromEntity(List<Lancamento> lancamentos) {
        return lancamentos.stream().map(lancamento -> fromEntity(lancamento)).collect(Collectors.toList());
    }

    public static Page<LancamentoDTO> fromEntity(Page<Lancamento> lancamentos) {
        List<LancamentoDTO> lancamentosFind = lancamentos.stream().map(lancamento -> fromEntity(lancamento)).collect(Collectors.toList());
        Page<LancamentoDTO> lancamentosDTO = new PageImpl<>(lancamentosFind, lancamentos.getPageable(), lancamentos.getTotalElements());
        return lancamentosDTO;
    }

}
