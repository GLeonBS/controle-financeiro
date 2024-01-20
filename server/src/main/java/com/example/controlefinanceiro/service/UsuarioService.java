package com.example.controlefinanceiro.service;

import com.example.controlefinanceiro.abstractions.ServiceCRUD;
import com.example.controlefinanceiro.domain.Usuario;
import com.example.controlefinanceiro.dto.UsuarioDTO;
import com.example.controlefinanceiro.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class UsuarioService extends ServiceCRUD<Usuario, UsuarioDTO> {
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }
}
