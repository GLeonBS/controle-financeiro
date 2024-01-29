package com.example.controlefinanceiro.domain;

import com.example.controlefinanceiro.abstractions.EntityCRUD;
import com.example.controlefinanceiro.dto.UsuarioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario extends EntityCRUD<UsuarioDTO, Usuario> {
    private String nome;

    private Date dataNascimento;

    private String email;

    private String senha;

    private String cargo;

    @Override
    public Usuario self(UsuarioDTO dto) {
        return new Usuario(dto.nome(), dto.dataNascimento(), dto.email(), dto.senha(), dto.cargo());
    }
}
