package com.github.tomboyo.cautioustrain.profile;

import com.github.tomboyo.cautioustrain.AmqContainer;
import com.github.tomboyo.cautioustrain.spring.JmsTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.github.tomboyo.cautioustrain.profile.JmsTransform.toJmsProfile;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@Testcontainers
@JmsTest
public class ProfileJmsListernerIT {

  @Container private static final AmqContainer<?> AMQ = new AmqContainer<>();

  @DynamicPropertySource
  private static void amqpProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.artemis.broker-url", AMQ::getTcpUrl);
    registry.add("spring.artemis.user", AMQ::getUser);
    registry.add("spring.artemis.password", AMQ::getPassword);
  }

  @MockBean private ProfileRepository repository;
  @Autowired private JmsTemplate template;

  @Test
  public void messageIsSaved() {
    var profile = new JmsProfile("sam");

    template.convertAndSend("profiles", profile);

    verify(repository, timeout(5_000)).save(argThat(x -> toJmsProfile(x).equals(profile)));
  }
}
