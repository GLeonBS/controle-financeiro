package br.com.controle_financeiro.modules.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.controle_financeiro.abstractions.ServiceCRUD;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.modules.usuario.repository.UsuarioRepository;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Setter
@Service
public class UsuarioCRUDService extends ServiceCRUD<UsuarioEntity> {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioCRUDService(UsuarioRepository repository) {
        super(repository);
    }

    @Override
    public UsuarioEntity create(@NotNull UsuarioEntity request) {
        try {

            repository.findByEmail(request.getEmail())
                    .ifPresent(usuario -> {
                        throw new IllegalArgumentException("Email já cadastrado");
                    });

            request.setSenha(passwordEncoder.encode(request.getSenha()));
            return repository.save(request);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
