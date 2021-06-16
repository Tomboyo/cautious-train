package com.github.tomboyo.cautioustrain;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class PostgresContainer {
  public static PostgreSQLContainer<?> get() {
    return new PostgreSQLContainer<>(DockerImageName.parse("postgres:13"));
  }
}
