package br.com.controle_financeiro.abstractions;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryCRUD<R> extends JpaRepository<R, UUID> {

}
