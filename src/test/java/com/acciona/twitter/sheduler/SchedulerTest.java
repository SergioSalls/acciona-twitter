package com.acciona.twitter.sheduler;

import com.acciona.twitter.configurations.TwitterPropertiesConfig;
import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.services.HashtagService;
import com.acciona.twitter.services.TweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import twitter4j.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class SchedulerTest {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private TwitterPropertiesConfig twitterProperties;

    @Autowired
    private Twitter twitter;

    @MockBean
    private TweetService tweetService;

    @MockBean
    private HashtagService hashtagService;

    @Test
    void getTweets() throws TwitterException {
        scheduler.getTweets();

        verify(tweetService, atLeastOnce()).saveTweet(any(TweetEntity.class));
        verify(hashtagService, atLeastOnce()).saveHashtag(anyString());
    }

}