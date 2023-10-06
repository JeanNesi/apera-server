package com.apera.aperaserver.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ativo")
public class Ativo extends EntityId {
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;
    @Column(name = "logo")
    private String logo;
    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @OneToOne(mappedBy = "ativo")
    private Lancamento lancamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
