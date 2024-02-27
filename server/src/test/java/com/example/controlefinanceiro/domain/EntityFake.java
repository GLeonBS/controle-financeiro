package com.example.controlefinanceiro.domain;

import com.example.controlefinanceiro.abstractions.EntityCRUD;
import com.example.controlefinanceiro.dto.DTOFake;

import jakarta.persistence.Entity;

@Entity
public class EntityFake extends EntityCRUD<DTOFake, EntityFake> {

    private String nome;

    private String email;

    private String senha;

    public EntityFake(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public EntityFake() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
