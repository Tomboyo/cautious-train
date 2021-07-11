# Cautious Train

This is a sandbox project to work with a few different technologies. Goals are as follows:

- [x] Integrate with PostgreSQL via a DataJPA repository
    - [x] Set up ITs against PostgreSQL using Testcontainers
    - [x] Instrument schema migrations using Flyway
        - [x] Incorporate migrations into ITs
- [x] Integrate with Artemis via Spring JMS
    - [x] Set up ITs against Artemis using Testcontainers
    - [ ] How should we manage destination names (i.e., other than with brittle string literals)
    - [ ] Configure message retry:
        - [ ] If a processing attempt encounters an exception, redeliver the message for another attempt.
        - [ ] If a message cannot be processed after some small number of attempts, move it to a DLQ for later analysis.
    - [ ] Configure an upper bound on the length or size of a queue to prevent memory exhaustion in a disaster scenario.
- [ ] Integrate with MongoDB via (Spring Data
  MongoDB)[https://docs.spring.io/spring-data/mongodb/docs/3.2.2/reference/html/#mongo.core]
    - [ ] Set up ITs against MongoDB using Testcontainers
- [ ] (Needs research) Integrate with unleashed/togglz to practice with feature flags

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
