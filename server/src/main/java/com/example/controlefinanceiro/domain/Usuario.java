package com.example.controlefinanceiro.domain;

import java.util.Date;

import com.example.controlefinanceiro.abstractions.EntityCRUD;
import com.example.controlefinanceiro.dto.usuario.UsuarioDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario extends EntityCRUD<UsuarioDTO, Usuario> {
    private String nome;

    private Date dataNascimento;

    private String email;

    private String senha;

    private String cargo;
}
