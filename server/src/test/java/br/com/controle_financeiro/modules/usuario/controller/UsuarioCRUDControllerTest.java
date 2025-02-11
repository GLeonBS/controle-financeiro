package br.com.controle_financeiro.modules.usuario.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import config.ContainerEnviroment;
import config.IntegrationTest;
import jakarta.servlet.ServletException;
import utils.Fixtures;
import utils.TestUtils;

@IntegrationTest
@Sql(statements = "INSERT INTO usuario (id, nome, email, data_nascimento, senha, roles, created_at) VALUES ('50b558b6-806e-414e-8bee-79b0e06ea684', 'Teste', 'teste@teste.com', '2021-01-01','123456', '{ADMIN}', '2025-01-20 16:35:00');")
public class UsuarioCRUDControllerTest extends ContainerEnviroment {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRejeitarUmUsuarioComEmailCadastrado() throws Exception {
        UsuarioEntity usuario = Fixtures.createUsuarioEntity();
        usuario.setEmail("teste@teste.com");

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(post("/usuario")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(TestUtils.objectToJson(usuario)))
                        .andExpect(status().isBadRequest())).isInstanceOf(ServletException.class)
                .hasMessageContaining("Email já cadastrado");
    }
}
