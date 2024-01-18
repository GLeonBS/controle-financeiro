package com.example.service;

import com.example.abstractions.ServiceCRUD;
import com.example.domain.Usuario;
import com.example.dto.UsuarioDTO;
import com.example.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends ServiceCRUD<Usuario, UsuarioDTO> {

    public UsuarioService(ModelMapper modelMapper, UsuarioRepository repository, Usuario entity) {
        super(modelMapper, repository, entity);
    }
}
