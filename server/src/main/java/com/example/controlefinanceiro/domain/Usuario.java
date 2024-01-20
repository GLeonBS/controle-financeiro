package com.example.controlefinanceiro.domain;

import com.example.controlefinanceiro.abstractions.EntityControleFinanceiro;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario extends EntityControleFinanceiro {
    private String nome;

    private Date dataNascimento;

    private String email;

    private String senha;

    private String cargo;
}
