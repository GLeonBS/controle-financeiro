package br.com.controle_financeiro.modules.usuario.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.controle_financeiro.abstractions.RepositoryCRUD;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends RepositoryCRUD<UsuarioEntity> {

    Optional<UsuarioEntity> findByEmail(String email);

}
