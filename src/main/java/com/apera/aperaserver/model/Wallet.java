package com.apera.aperaserver.model;

import com.apera.aperaserver.enterprise.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "wallet")
public class Wallet extends EntityId implements CheckRequiredFields{
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "wallet")
    private List<Release> releases;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
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
        if(user == null || name == null){
            throw new ResourceNotFoundException("Campo obrigatório não fornecido.");
        }
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "name='" + name + '\'' +
                ", releases=" + releases +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
