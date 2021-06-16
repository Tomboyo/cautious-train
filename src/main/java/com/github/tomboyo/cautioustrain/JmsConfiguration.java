package com.github.tomboyo.cautioustrain;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration(proxyBeanMethods = false)
@EnableJms
public class JmsConfiguration {}
