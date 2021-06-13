package com.github.tomboyo.cautioustrain.repository;

import com.github.tomboyo.cautioustrain.model.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ContextConfiguration(initializers = ProfileRepositoryIT.Initializer.class)
public class ProfileRepositoryIT {

  @Container
  private static final PostgreSQLContainer<?> POSTGRES =
      new PostgreSQLContainer<>(DockerImageName.parse("postgres:13"))
          .withFileSystemBind(
              "./docker-compose/initdb.sh",
              "/docker-entrypoint-initdb.d/initdb.sh",
              BindMode.READ_ONLY);

  public static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of(
              "spring.datasource.username: username",
              "spring.datasource.password: password",
              "spring.datasource.url: " + POSTGRES.getJdbcUrl())
          .applyTo(applicationContext.getEnvironment());
    }
  }

  @Autowired ProfileRepository repository;

  @Test
  public void shouldSaveAndFetchProfile() {
    var profile = new Profile().name("Sam");

    repository.save(profile);
    Assertions.assertEquals(profile, repository.findById(profile.getId()).orElseThrow());
  }
}
