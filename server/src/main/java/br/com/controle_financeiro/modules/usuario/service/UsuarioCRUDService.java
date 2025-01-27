package br.com.controle_financeiro.modules.usuario.service;

import org.springframework.stereotype.Service;

import br.com.controle_financeiro.abstractions.ServiceCRUD;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.modules.usuario.repository.UsuarioRepository;

@Service
public class UsuarioCRUDService extends ServiceCRUD<UsuarioEntity> {

    public UsuarioCRUDService(UsuarioRepository repository) {
        super(repository);
    }
}
