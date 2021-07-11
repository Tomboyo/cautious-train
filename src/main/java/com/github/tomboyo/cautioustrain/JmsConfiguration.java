package com.github.tomboyo.cautioustrain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@EnableJms
public class JmsConfiguration {
  @Bean
  public MessageConverter jacksonMessageConverter() {
    var converter = new MappingJackson2MessageConverter();
    converter.setTypeIdPropertyName("_type");
    return converter;
  }
}
