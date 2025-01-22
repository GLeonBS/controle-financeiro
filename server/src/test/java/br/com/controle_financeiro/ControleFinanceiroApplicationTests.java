package br.com.controle_financeiro;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import utils.config.ContainerEnviroment;

@ActiveProfiles("test")
@SpringBootTest
class ControleFinanceiroApplicationTests extends ContainerEnviroment {

    @Test
    void contextLoads() {
    }

}
