package br.com.controle_financeiro.abstractions;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.com.controle_financeiro.usuario.entity.UsuarioEntity;
import utils.Fixtures;
import utils.TestUtils;
import utils.config.ContainerEnviroment;
import utils.config.IntegrationTest;

@IntegrationTest
class ControllerCRUDTest extends ContainerEnviroment {

    @Autowired
    private MockMvc mockMvc;

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
    void deveRejeitarUmDTOComCamposNaoValidados() throws Exception {
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
    @Sql(statements = "INSERT INTO usuario (id, nome, email, data_nascimento) VALUES ('50b558b6-806e-414e-8bee-79b0e06ea684', 'Teste', 'teste@teste.com', '2021-01-01');")
    void deveRetornarUmaPaginaDeEntidades() throws Exception {

        this.mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id", is("50b558b6-806e-414e-8bee-79b0e06ea684")))
                .andExpect(jsonPath("$.content[0].nome", is("Teste")))
                .andExpect(jsonPath("$.content[0].email", is("teste@teste.com")))
                .andExpect(jsonPath("$.content[0].dataNascimento", is("2021-01-01")))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.size", is(20)))
                .andExpect(jsonPath("$.number", is(0)))
                .andDo(MockMvcResultHandlers.print());
    }
}
