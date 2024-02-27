package com.example.controlefinanceiro;

import com.example.controlefinanceiro.config.ContainerEnviroment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = ControleFinanceiroApplication.class)
@ActiveProfiles("test")
class ControleFinanceiroApplicationTests extends ContainerEnviroment {

    @Autowired
    ControleFinanceiroApplication controleFinanceiroApplication;

    @Test
    void contextLoads() {
        assertThat(controleFinanceiroApplication).isNotNull();
    }

}
