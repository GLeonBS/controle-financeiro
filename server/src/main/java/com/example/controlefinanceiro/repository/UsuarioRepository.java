package com.example.controlefinanceiro.repository;

import org.springframework.stereotype.Repository;

import com.example.controlefinanceiro.domain.Usuario;
import com.example.controlefinanceiro.interfaces.RepositoryCRUD;

@Repository
public interface UsuarioRepository extends RepositoryCRUD<Long, Usuario> {
}
