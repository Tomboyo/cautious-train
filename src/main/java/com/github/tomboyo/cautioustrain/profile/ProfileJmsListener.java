package com.github.tomboyo.cautioustrain.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ProfileJmsListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProfileJmsListener.class);

  private final ProfileRepository profileRepository;

  @Autowired
  public ProfileJmsListener(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  @JmsListener(destination = "profiles")
  public void consume(JmsProfile profile) {
    LOGGER.info("Received profile: profile={}", profile);
    profileRepository.save(JmsTransform.toModelProfile(profile));
  }
}
