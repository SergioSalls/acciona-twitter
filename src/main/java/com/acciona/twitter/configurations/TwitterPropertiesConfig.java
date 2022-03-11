package com.acciona.twitter.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties("twitter")
public class TwitterPropertiesConfig {

    private Integer minfollowers;
    private List<String> languages;
}