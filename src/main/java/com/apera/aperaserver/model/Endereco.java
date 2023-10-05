package com.apera.aperaserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Endereco extends EntityId{
    @Column(name = "pais", length = 100, nullable = false)
    private String pais;
    @Column(name = "uf", length = 100, nullable = false)
    private String uf;
    @Column(name = "cidade", length = 100, nullable = false)
    private String cidade;
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;
    @Column(name = "bairro",length = 100, nullable = false)
    private String bairro;
    @Column(name = "rua", length = 100, nullable = false)
    private String rua;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "complemento", length = 50)
    private String complemento;
}
