package com.apera.aperaserver.resource.representation;

import com.apera.aperaserver.model.Asset;
import com.apera.aperaserver.model.Release;
import com.apera.aperaserver.model.ReleaseType;
import com.apera.aperaserver.model.Wallet;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReleaseDTO {
    private Double amount;
    private Double extraCosts;
    private Double price;
    private ReleaseType releaseType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AssetsDTO asset;
    //private Wallet wallet;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getExtraCosts() {
        return extraCosts;
    }

    public void setExtraCosts(Double extraCosts) {
        this.extraCosts = extraCosts;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ReleaseType getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(ReleaseType releaseType) {
        this.releaseType = releaseType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AssetsDTO getAsset() {
        return asset;
    }

    public void setAsset(AssetsDTO asset) {
        this.asset = asset;
    }

    public ReleaseDTO() {
    }

    public static ReleaseDTO fromEntity(Release release) {
        ReleaseDTO releaseDTO = new ReleaseDTO();
        releaseDTO.setAsset(AssetsDTO.fromEntity(release.getAsset()));
        releaseDTO.setExtraCosts(release.getExtraCosts());
        releaseDTO.setReleaseType(release.getReleaseType());
        releaseDTO.setPrice(release.getPrice());
        releaseDTO.setAmount(releaseDTO.getAmount());
        releaseDTO.setUpdatedAt(release.getUpdatedAt());
        releaseDTO.setCreatedAt(release.getCreatedAt());
        return releaseDTO;
    }

    public static List<ReleaseDTO> fromEntity(List<Release> releases) {
        return releases.stream().map(ReleaseDTO::fromEntity).collect(Collectors.toList());
    }

}
