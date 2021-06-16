package com.github.tomboyo.cautioustrain.jms;

import com.github.tomboyo.cautioustrain.repository.ProfileRepository;
import com.github.tomboyo.cautioustrain.model.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ProfileJmsListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProfileJmsListener.class);

  private final ProfileRepository profileRepository;

  @Autowired
  public ProfileJmsListener(
      ProfileRepository profileRepository
  ) {
    this.profileRepository = profileRepository;
  }

  @JmsListener(destination = "profiles")
  public void consume(Profile profile) {
    LOGGER.info("Received profile: profile={}", profile);
    profileRepository.save(profile);
    LOGGER.info("Persisted profile: profile={}", profile);
  }
}
