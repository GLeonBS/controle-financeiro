package br.com.controle_financeiro.config;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import br.com.controle_financeiro.config.container.PostgresTestContainer;

@Testcontainers
public class ContainerEnviroment {

    @Container
    public static PostgreSQLContainer<PostgresTestContainer> postgreSQLContainer = PostgresTestContainer.getInstance();

}
