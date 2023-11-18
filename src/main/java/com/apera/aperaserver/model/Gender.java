package com.apera.aperaserver.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "genders")
public class Gender extends EntityId{
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EGender name;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public EGender getName() {
        return name;
    }

    public void setName(EGender name) {
        this.name = name;
    }
}
