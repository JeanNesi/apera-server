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
    private Double amount;
    private Double price;
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

    public Double getAmount() {
        return amount;
    }

    public void setQuantidade(Double amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPreco(Double price) {
        this.price = price;
    }

    public ReleaseType getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(ReleaseType releaseType) {
        this.releaseType = releaseType;
    }

    public ReleaseDTO() {
    }

    public static ReleaseDTO fromEntity(Release release) {
        ReleaseDTO releaseDTO = new ReleaseDTO();
        releaseDTO.setId(release.getId());
        releaseDTO.setAtivo(release.getAsset());
        releaseDTO.setQuantidade(release.getAmount());
        releaseDTO.setPreco(release.getPrice());
        releaseDTO.setReleaseType(release.getReleaseType());
        return releaseDTO;
    }

    public static List<ReleaseDTO> fromEntity(List<Release> releases) {
        return releases.stream().map(lancamento -> fromEntity(lancamento)).collect(Collectors.toList());
    }

    public static Page<ReleaseDTO> fromEntity(Page<Release> releases) {
        List<ReleaseDTO> realeasesFind = releases.stream().map(release -> fromEntity(release)).collect(Collectors.toList());
        Page<ReleaseDTO> releaseDTO = new PageImpl<>(realeasesFind, releases.getPageable(), releases.getTotalElements());
        return releaseDTO;
    }

}
