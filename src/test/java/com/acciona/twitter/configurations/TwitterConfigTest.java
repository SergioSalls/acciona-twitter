package com.acciona.twitter.configurations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import twitter4j.Twitter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TwitterConfigTest {

    @Autowired
    private TwitterConfig twitterConfig;

    @Test
    private void twitter() {
        Twitter twitter = twitterConfig.twitter();
        assertNotNull(twitter);
    }
}