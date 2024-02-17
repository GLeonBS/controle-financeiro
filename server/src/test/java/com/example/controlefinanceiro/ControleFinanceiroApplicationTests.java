package com.example.controlefinanceiro;

import container.ContainerTesteIntegracao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ControleFinanceiroApplicationTests extends ContainerTesteIntegracao {

	@Autowired
	ControleFinanceiroApplication controleFinanceiroApplication;
	@Test
	void contextLoads() {
		assertThat(controleFinanceiroApplication).isNotNull();
	}

}
