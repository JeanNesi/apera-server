package com.apera.aperaserver.model;

import com.apera.aperaserver.enterprise.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "release")
public class Release extends EntityId implements CheckRequiredFields {
    @Column(name = "amount", length = 10, nullable = false)
    private Double amount;
    @Column(name = "extra_costs", length = 10)
    private Double extraCosts;
    @Column(name = "price", length = 100, nullable = false)
    private Double price;
    @Column(name = "release_type", length = 100, nullable = false)
    private ReleaseType releaseType;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Embedded
    private Asset asset;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;


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

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
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

    @Override
    public void checkRequiredFields() {
        if(amount == null || price == null || releaseType == null){
            throw new ResourceNotFoundException("Campo obrigatório não fornecido.");
        }
    }
}
