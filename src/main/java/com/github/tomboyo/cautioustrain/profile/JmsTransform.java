package com.github.tomboyo.cautioustrain.profile;

public class JmsTransform {
  private JmsTransform() {}

  public static Profile toModelProfile(JmsProfile x) {
    return new Profile().name(x.getName());
  }

  public static JmsProfile toJmsProfile(Profile x) {
    return new JmsProfile(x.getName());
  }
}
