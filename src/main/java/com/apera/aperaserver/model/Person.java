package com.apera.aperaserver.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person extends Profile {
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;
    @Column(name = "birth_date")
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private EGender EGender;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public EGender getGender() {
        return EGender;
    }

    public void setGender(EGender EGender) {
        this.EGender = EGender;
    }
}
