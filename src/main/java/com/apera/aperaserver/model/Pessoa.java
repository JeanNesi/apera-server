package com.apera.aperaserver.model;

import javax.persistence.*;

@Entity
public class Pessoa extends EntityId {
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;
    @Column(name = "data_nascimento", length = 10, nullable = false)
    private String dataNascimento;
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;
    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    @Column(name = "telefone", length = 11)
    private String telefone;
    @Column(name = "renda", nullable = false)
    private Double renda;
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }
}
