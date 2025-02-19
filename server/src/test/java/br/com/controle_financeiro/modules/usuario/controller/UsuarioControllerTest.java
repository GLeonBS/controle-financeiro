package br.com.controle_financeiro.modules.usuario.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import config.ContainerEnviroment;
import config.IntegrationTest;
import utils.Fixtures;
import utils.TestUtils;

@IntegrationTest
@Sql(statements = "INSERT INTO usuario (id, nome, email, data_nascimento, senha, roles, created_at) VALUES ('50b558b6-806e-414e-8bee-79b0e06ea684', 'Teste', 'teste@teste.com', '2021-01-01','123456', '{ROLE_ADMIN, ROLE_USER}', '2025-01-20 16:35:00');")
public class UsuarioControllerTest extends ContainerEnviroment {

    private String token;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        this.token = TestUtils.generateToken("teste@teste.com", "usuario_secret_test");
    }

    @Test
    void deveRejeitarUmUsuarioComEmailCadastrado() throws Exception {
        UsuarioEntity usuario = Fixtures.createUsuarioEntity();
        usuario.setEmail("teste@teste.com");

        this.mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson(usuario)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(content().string(containsString("Email já cadastrado")));
    }

    @Test
    void deveCriarUmUsuario() throws Exception {

        UsuarioEntity usuarioEntity = Fixtures.createUsuarioEntity();

        this.mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson(usuarioEntity)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRejeitarUmaEntidadeComCamposNaoValidados() throws Exception {
        Map<String, String> expectedErrors = Map.of("nome", "Insira um nome!");

        UsuarioEntity usuario = Fixtures.createUsuarioEntity();
        usuario.setNome("");

        this.mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson(usuario)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(TestUtils.objectToJson(expectedErrors)));

    }

    @Test
    void deveEncontrarPeloId() throws Exception {

        this.mockMvc.perform(get("/usuario/50b558b6-806e-414e-8bee-79b0e06ea684")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", is("Teste")))
                .andExpect(jsonPath("$.email", is("teste@teste.com")))
                .andExpect(jsonPath("$.dataNascimento", is("2021-01-01")))
                .andExpect(jsonPath("$.createdAt", is("2025-01-20T16:35:00")));
    }

    @Test
    void deveAtualizarAEntidade() throws Exception {

        UsuarioEntity usuario = Fixtures.createUsuarioEntity();

        this.mockMvc.perform(put("/usuario/50b558b6-806e-414e-8bee-79b0e06ea684")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson(usuario))
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome", is("Vergil")))
                .andExpect(jsonPath("$.email", is("vergil@teste.com")))
                .andExpect(jsonPath("$.dataNascimento", is("2001-08-23")))
                .andExpect(jsonPath("$.createdAt", is("2025-01-20T16:35:00")));
    }

    @Test
    void deveDeletarAEntidade() throws Exception {

        this.mockMvc.perform(delete("/usuario/50b558b6-806e-414e-8bee-79b0e06ea684")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isNoContent());
    }
}
