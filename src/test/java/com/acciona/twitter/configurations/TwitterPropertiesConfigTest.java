package com.acciona.twitter.configurations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class TwitterPropertiesConfigTest {

    @Autowired
    private TwitterPropertiesConfig twitterPropertiesConfig;

    @Test
    void twitterPropertiesConfig() {
        assertNotNull(twitterPropertiesConfig.getQueryCount());
        assertNotNull(twitterPropertiesConfig.getMinFollowers());
        assertNotNull(twitterPropertiesConfig.getMinHashtagCount());
        assertNotNull(twitterPropertiesConfig.getMaxTweetPage());
        assertNotNull(twitterPropertiesConfig.getLanguages());
    }
}