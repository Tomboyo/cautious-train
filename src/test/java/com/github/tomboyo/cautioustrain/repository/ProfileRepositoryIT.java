package com.github.tomboyo.cautioustrain.repository;

import com.github.tomboyo.cautioustrain.PostgresContainer;
import com.github.tomboyo.cautioustrain.model.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("test")
public class ProfileRepositoryIT {

  @Container static final PostgreSQLContainer<?> POSTGRES = PostgresContainer.get();

  @DynamicPropertySource
  static void postgresProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
    registry.add("spring.flyway.user", POSTGRES::getUsername);
    registry.add("spring.flyway.password", POSTGRES::getPassword);
  }

  @Autowired ProfileRepository repository;

  @Test
  public void shouldSaveAndFetchProfile() {
    var profile = new Profile().name("Sam");

    repository.save(profile);
    Assertions.assertEquals(profile, repository.findById(profile.getId()).orElseThrow());
  }
}
