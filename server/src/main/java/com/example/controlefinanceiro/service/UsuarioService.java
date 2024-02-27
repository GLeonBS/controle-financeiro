package com.example.controlefinanceiro.service;

import org.springframework.stereotype.Service;

import com.example.controlefinanceiro.abstractions.ServiceCRUD;
import com.example.controlefinanceiro.domain.Usuario;
import com.example.controlefinanceiro.dto.usuario.UsuarioDTO;
import com.example.controlefinanceiro.repository.UsuarioRepository;

@Service
public class UsuarioService extends ServiceCRUD<Usuario, UsuarioDTO, UsuarioRepository> {

    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }
}
