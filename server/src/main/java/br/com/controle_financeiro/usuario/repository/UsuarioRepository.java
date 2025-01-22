package br.com.controle_financeiro.usuario.repository;

import org.springframework.stereotype.Repository;

import br.com.controle_financeiro.abstractions.RepositoryCRUD;
import br.com.controle_financeiro.usuario.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends RepositoryCRUD<UsuarioEntity> {
}
