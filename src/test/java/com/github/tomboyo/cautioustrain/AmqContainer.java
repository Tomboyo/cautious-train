package com.github.tomboyo.cautioustrain;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class AmqContainer<SELF extends AmqContainer<SELF>> extends GenericContainer<SELF> {

  private static final String USER = "admin";
  private static final String PASSWORD = "password";

  public AmqContainer() {
    super(DockerImageName.parse("registry.redhat.io/amq7/amq-broker:7.5"));

    addEnv("AMQ_USER", USER);
    addEnv("AMQ_PASSWORD", PASSWORD);

    addExposedPort(61616);
  }

  /**
   * Start the container with the given anycast queues.
   *
   * @return the container for chaining
   */
  public AmqContainer<SELF> withQueues(String queues) {
    addEnv("AMQ_QUEUES", queues);
    return this;
  }

  public String getUser() {
    return USER;
  }

  public String getPassword() {
    return PASSWORD;
  }

  public String getTcpUrl() {
    return "tcp://" + getContainerIpAddress() + ":" + getMappedPort(61616);
  }
}
