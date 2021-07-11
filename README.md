# Cautious Train

This is a sandbox project to work with the following:

- Spring Boot (JMS)
- Spring Data JPA
- Testcontainers

Github randomly generated the name.

## Design

### Database

Flyway manages database migrations via an administrator account (`spring.flyway.user`). The application otherwise
connects to the database using the application account (`spring.datasource.username`) with minimal permissions.

We perform database integration tests using a `PostgreSQLContainer`. These tests must set the `spring.flyway.`
and `spring.datasource` parameters via `@DynamicPropertySource`.

## Troubleshooting Tests

### Could not find a valid Docker environment.

This is because docker is not started. Run `systemctl start docker` to resolve.

### Can not connect to Ryuk at localhost:<ephemeral port>

This is caused by an incompatibility between testcontainers and your docker engine. Update to the latest version
available. The following combination works on Fedora: testcontainers 1.15.3 with docker 20.10.3.
