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

    private Integer queryCount;
    private Integer minFollowers;
    private Integer minHashtagCount;
    private Integer maxTweetPage;
    private List<String> languages;
}