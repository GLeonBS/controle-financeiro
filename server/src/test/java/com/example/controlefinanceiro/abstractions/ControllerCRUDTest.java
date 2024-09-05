package com.example.controlefinanceiro.abstractions;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controlefinanceiro.config.ContainerEnviroment;
import com.example.controlefinanceiro.config.ControllerTest;
import com.example.controlefinanceiro.dto.DTOFake;

import utils.TestUtils;

import static com.example.controlefinanceiro.fixtures.FixtureDTOs.createDTOFake;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest
class ControllerCRUDTest extends ContainerEnviroment {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarUmUsuario() throws Exception {

        DTOFake dtoFake = createDTOFake();

        this.mockMvc.perform(post("/controller-fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson(dtoFake)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRejeitarUmDTOComCamposNaoValidados() throws Exception {
        Map<String, String> expectedErrors = Map.of("nome", "Insira um nome!");
        
        DTOFake dtoFake = new DTOFake(null, "dtofake@gmail.com", "123");

        this.mockMvc.perform(post("/controller-fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson(dtoFake)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(TestUtils.objectToJson(expectedErrors)));

    }

    @Test
    void deveRetornarUmaPaginaDeDtos() throws Exception {

        this.mockMvc.perform(get("/controller-fake"))
                .andExpect(status().isOk());
    }
}
