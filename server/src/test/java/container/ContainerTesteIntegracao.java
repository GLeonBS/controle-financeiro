package container;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.ClassRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = {ContainerTesteIntegracao.Initializer.class})
@Transactional
@Rollback
public abstract class ContainerTesteIntegracao {

    @Autowired
    private EntityManager entityManager;

    private static DockerImageName myImage = DockerImageName.parse("postgresql:1.19.5").asCompatibleSubstituteFor(
            "postgres");

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(myImage)
            .withDatabaseName("controle-financeiro-teste")
            .withUsername("postgres")
            .withPassword("postgres");

    public EntityManager getEntityManager() {
        return entityManager;
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
