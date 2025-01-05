package br.com.controle_financeiro.abstractions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import br.com.controle_financeiro.config.ControllerTest;

@ControllerTest
class ControllerCRUDTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarUmUsuario() throws Exception {

        //        this.mockMvc.perform(post("/controller-fake")
        //                        .contentType(MediaType.APPLICATION_JSON)
        //                        .accept(MediaType.APPLICATION_JSON)
        //                        .content(TestUtils.objectToJson(dtoFake)))
        //                .andExpect(status().isCreated());
    }

    @Test
    void deveRejeitarUmDTOComCamposNaoValidados() throws Exception {
        //        Map<String, String> expectedErrors = Map.of("nome", "Insira um nome!");
        //
        //        DTOFake dtoFake = new DTOFake(null, "dtofake@gmail.com", "123");
        //
        //        this.mockMvc.perform(post("/controller-fake")
        //                        .contentType(MediaType.APPLICATION_JSON)
        //                        .accept(MediaType.APPLICATION_JSON)
        //                        .content(TestUtils.objectToJson(dtoFake)))
        //                .andExpect(status().isBadRequest())
        //                .andExpect(content().string(TestUtils.objectToJson(expectedErrors)));

    }

    @Test
    void deveRetornarUmaPaginaDeDtos() throws Exception {

        //        this.mockMvc.perform(get("/controller-fake"))
        //                .andExpect(status().isOk());
    }
}
