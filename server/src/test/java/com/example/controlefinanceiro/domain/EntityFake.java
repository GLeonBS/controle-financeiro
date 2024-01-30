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

    @Override
    public EntityFake self(DTOFake dto) {
        return new EntityFake(dto.nome(), dto.email(), dto.senha());
    }
}
