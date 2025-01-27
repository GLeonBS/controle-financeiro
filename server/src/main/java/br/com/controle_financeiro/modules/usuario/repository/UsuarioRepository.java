package br.com.controle_financeiro.modules.usuario.repository;

import org.springframework.stereotype.Repository;

import br.com.controle_financeiro.abstractions.RepositoryCRUD;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends RepositoryCRUD<UsuarioEntity> {
}
