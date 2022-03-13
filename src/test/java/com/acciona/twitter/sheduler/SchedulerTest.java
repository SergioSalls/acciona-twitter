package com.acciona.twitter.sheduler;

import com.acciona.twitter.configurations.TwitterPropertiesConfig;
import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.services.HashtagService;
import com.acciona.twitter.services.TweetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import twitter4j.*;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
/*@SpringBootTest*/
@ExtendWith(MockitoExtension.class)
public class SchedulerTest {

    @InjectMocks
    private Scheduler scheduler;

    @Mock
    private TwitterPropertiesConfig twitterProperties;

    @Mock
    private Twitter twitter;

    @Mock
    private TweetService tweetService;

    @Mock
    private HashtagService hashtagService;

    //TODO SLS: Mirar si da tiempo
    /*@BeforeEach
    public void before(){
    }
*/
    @Test
    void getTweets() throws TwitterException {
        QueryResult queryResult = mock(QueryResult.class);
        Status elmundo = mock(Status.class);
        HashtagEntity hashtagEntity = mock(HashtagEntity.class);
        HashtagEntity[] hashtagEntities = new HashtagEntity[]{ hashtagEntity };

        given(elmundo.getId()).willReturn(1L);
        given(elmundo.getUser()).willReturn(mock(User.class));
        given(elmundo.getUser().getId()).willReturn(2L);
        given(elmundo.getUser().getName()).willReturn("elmundo");
        given(elmundo.getText()).willReturn("TST1");
        given(elmundo.getUser().getLocation()).willReturn("España");
        given(elmundo.getLang()).willReturn("es");
        given(elmundo.getUser().getFollowersCount()).willReturn(1600);
        given(elmundo.getHashtagEntities()).willReturn(hashtagEntities);
        given(hashtagEntity.getText()).willReturn("TST");

        given(twitterProperties.getUsersToSearch()).willReturn(Collections.singletonList("elmundo"));
        given(twitterProperties.getUserQueryCount()).willReturn(10);
        given(twitterProperties.getLanguages()).willReturn(Collections.singletonList("es"));
        given(twitterProperties.getMinFollowers()).willReturn(1500);
        given(twitter.search(any(Query.class))).willReturn(queryResult);
        given(tweetService.existTweetById(any())).willReturn(false);

        given(queryResult.getTweets()).willReturn(Collections.singletonList(elmundo));

        scheduler.getTweets();

        verify(tweetService).saveTweet(any(TweetEntity.class));
        verify(hashtagService).saveHashtag(anyString());

    }

}