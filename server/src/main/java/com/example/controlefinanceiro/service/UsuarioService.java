package com.example.controlefinanceiro.service;

import com.example.controlefinanceiro.abstractions.ServiceCRUD;
import com.example.controlefinanceiro.domain.Usuario;
import com.example.controlefinanceiro.dto.UsuarioDTO;
import com.example.controlefinanceiro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends ServiceCRUD<Usuario, UsuarioDTO, UsuarioRepository> {

    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }
}