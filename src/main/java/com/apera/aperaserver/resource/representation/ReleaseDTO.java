package com.apera.aperaserver.resource.representation;

import com.apera.aperaserver.model.Asset;
import com.apera.aperaserver.model.Release;
import com.apera.aperaserver.model.ReleaseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ReleaseDTO {
    private Long id;
    private Asset asset;
    private Double quantidade;
    private Double preco;
    private ReleaseType releaseType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Asset getAtivo() {
        return asset;
    }

    public void setAtivo(Asset asset) {
        this.asset = asset;
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

    public ReleaseType getTipoLancamento() {
        return releaseType;
    }

    public void setTipoLancamento(ReleaseType releaseType) {
        this.releaseType = releaseType;
    }

    public ReleaseDTO() {
    }

//    public static ReleaseDTO fromEntity(Release release) {
//        ReleaseDTO releaseDTO = new ReleaseDTO();
//        releaseDTO.setId(release.getId());
//        releaseDTO.setAtivo(release.getAtivo());
//        releaseDTO.setQuantidade(release.getQuantidade());
//        releaseDTO.setPreco(release.getPreco());
//        releaseDTO.setTipoLancamento(release.getTipoLancamento());
//        return releaseDTO;
//    }

//    public static List<ReleaseDTO> fromEntity(List<Release> releases) {
//        return releases.stream().map(lancamento -> fromEntity(lancamento)).collect(Collectors.toList());
//    }
//
//    public static Page<ReleaseDTO> fromEntity(Page<Release> lancamentos) {
//        List<ReleaseDTO> lancamentosFind = lancamentos.stream().map(lancamento -> fromEntity(lancamento)).collect(Collectors.toList());
//        Page<ReleaseDTO> lancamentosDTO = new PageImpl<>(lancamentosFind, lancamentos.getPageable(), lancamentos.getTotalElements());
//        return lancamentosDTO;
//    }

}
