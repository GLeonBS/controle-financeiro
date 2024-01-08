package com.example.domain;

import com.example.abstractions.EntityControleFinanceiro;
import jakarta.persistence.Table;

import java.util.Date;
@Table(name = "usuario")
public class Usuario extends EntityControleFinanceiro {

    private String nome;

    private Date dataNascimento;

    private String email;

    private String senha;

    private String cargo;
}
