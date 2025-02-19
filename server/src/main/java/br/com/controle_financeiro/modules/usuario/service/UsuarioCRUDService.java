package br.com.controle_financeiro.modules.usuario.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.controle_financeiro.exception.EntityNotFoundException;
import br.com.controle_financeiro.modules.usuario.dto.UsuarioResponseDTO;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.modules.usuario.repository.UsuarioRepository;
import br.com.controle_financeiro.utils.MyBeanUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class UsuarioCRUDService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO create(@NotNull UsuarioEntity request) {
        try {
            repository.findByEmail(request.getEmail())
                    .ifPresent(usuario -> {
                        throw new IllegalArgumentException("Email já cadastrado");
                    });

            request.setSenha(passwordEncoder.encode(request.getSenha()));

            UsuarioEntity usuarioSaved = repository.save(request);

            UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

            BeanUtils.copyProperties(usuarioSaved, usuarioResponseDTO);

            return usuarioResponseDTO;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public UsuarioResponseDTO findOne(@PathVariable @NotNull UUID id) {
        UsuarioEntity usuarioEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário", id.toString()));

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        BeanUtils.copyProperties(usuarioEntity, usuarioResponseDTO);

        return usuarioResponseDTO;
    }

    public UsuarioResponseDTO update(@NotNull UUID id, @NotNull UsuarioEntity object) {
        try {
            UsuarioEntity usuarioEntity = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário", id.toString()));

            MyBeanUtils.copyNonNullProperties(object, usuarioEntity);

            UsuarioEntity usuarioSaved = repository.save(usuarioEntity);

            UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

            BeanUtils.copyProperties(usuarioSaved, usuarioResponseDTO);

            return usuarioResponseDTO;
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(@PathVariable @NotNull UUID id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário", id.toString())));
    }
}
