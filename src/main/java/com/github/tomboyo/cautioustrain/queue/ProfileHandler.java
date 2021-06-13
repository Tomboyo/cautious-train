package com.github.tomboyo.cautioustrain.queue;

import com.github.tomboyo.cautioustrain.repository.ProfileRepository;
import com.github.tomboyo.cautioustrain.model.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ProfileHandler {

  private static Logger logger = LoggerFactory.getLogger(ProfileHandler.class);

  @Bean
  public Consumer<Profile> handler(ProfileRepository profileRepository) {
    return profile -> {
      logger.info("Received profile: profile={}", profile);
      profileRepository.save(profile);
      logger.info("Persisted profile: profile={}", profile);
    };
  }
}
