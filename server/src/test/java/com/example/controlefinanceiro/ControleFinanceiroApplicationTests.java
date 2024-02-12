package com.example.controlefinanceiro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ControleFinanceiroApplicationTests {

	@Autowired
	ControleFinanceiroApplication controleFinanceiroApplication;

	@Test
	void contextLoads() {
		assertThat(controleFinanceiroApplication).isNotNull();
	}

}
