package br.com.controle_financeiro.modules.usuario.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import config.ContainerEnviroment;
import config.IntegrationTest;

@IntegrationTest
@Sql(statements = "INSERT INTO usuario (id, nome, email, data_nascimento, senha, roles, created_at) VALUES ('50b558b6-806e-414e-8bee-79b0e06ea684', 'Teste', 'teste@teste.com', '2021-01-01','123456', '{ADMIN}', '2025-01-20 16:35:00');")
class UsuarioRepositoryTest extends ContainerEnviroment {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void deveEncontrarUsuarioPorEmail() {
        Optional<UsuarioEntity> user = usuarioRepository.findByEmail("teste@teste.com");

        assertThat(user).isPresent();
        assertThat(user.get().getEmail()).isEqualTo("teste@teste.com");
    }

    @Test
    void naoDeveEncontrarUsuarioPorEmail() {
        Optional<UsuarioEntity> user = usuarioRepository.findByEmail("testee@teste.com");

        assertThat(user).isEmpty();
    }
}