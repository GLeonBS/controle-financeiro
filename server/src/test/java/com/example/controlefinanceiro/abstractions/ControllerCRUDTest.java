package com.example.controlefinanceiro.abstractions;

import com.example.controlefinanceiro.controller.ControllerFake;
import com.example.controlefinanceiro.dto.DTOFake;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static com.example.controlefinanceiro.fixtures.FixtureDTOs.createDTOFake;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerCRUDTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ControllerFake controllerFake;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarUmUsuario() throws Exception {
        DTOFake dtoFake = createDTOFake();

        this.mockMvc.perform(post("/controller-fake")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoFake)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRejeitarUmDTOComCamposNaoValidados() throws Exception {
        DTOFake dtoFake = new DTOFake(null, "dtofake@gmail.com", "123");

        Map<String, String> expectedErrors = Map.of("nome", "Insira um nome!");
        this.mockMvc.perform(post("/controller-fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoFake)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedErrors)));

    }
}