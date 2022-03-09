package com.acciona.twitter.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twitter")
public class LanguagePropertiesConfig {

    private String languages;
}