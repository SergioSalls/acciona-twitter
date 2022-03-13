package com.acciona.twitter.services;

import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.entities.TwitterUserEntity;
import com.acciona.twitter.repositories.TweetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TweetServiceTest {

    @Autowired
    private TweetService tweetService;

    @MockBean
    private TweetRepository tweetRepository;

    private final static String USER_ID = "1";

    private TwitterUserEntity twitterUserEntity = TwitterUserEntity.builder()
            .id("1")
            .name("lefigaro")
            .build();
    private TweetEntity tweetEntity = TweetEntity.builder()
            .id("1")
            .user(twitterUserEntity)
            .text("TST")
            .location("Italia")
            .isValidated(true)
            .build();

    @Test
    void getAll() {
        PageImpl tweetEntityPaged = new PageImpl(Collections.singletonList(tweetEntity));
        given(tweetRepository.findAll(any(Pageable.class))).willReturn(tweetEntityPaged);

        Page<TweetEntity> tweets = tweetService.getAll(0);

        assertEquals(tweets.getTotalElements(), 1);
        assertEquals(tweets.getContent().get(0).getId(), tweetEntity.getId());
        assertEquals(tweets.getContent().get(0).getUser().getId(), tweetEntity.getUser().getId());
        assertEquals(tweets.getContent().get(0).getUser().getName(), tweetEntity.getUser().getName());
        assertEquals(tweets.getContent().get(0).getText(), tweetEntity.getText());
        assertEquals(tweets.getContent().get(0).getLocation(), tweetEntity.getLocation());
        assertEquals(tweets.getContent().get(0).getIsValidated(), tweetEntity.getIsValidated());
    }

    @Test
    void get() {
        given(tweetRepository.findById(anyString())).willReturn(Optional.of(tweetEntity));

        TweetEntity result = tweetService.get(USER_ID);

        assertEquals(result.getId(), tweetEntity.getId());
        assertEquals(result.getUser().getId(), tweetEntity.getUser().getId());
        assertEquals(result.getUser().getName(), tweetEntity.getUser().getName());
        assertEquals(result.getText(), tweetEntity.getText());
        assertEquals(result.getLocation(), tweetEntity.getLocation());
        assertEquals(result.getIsValidated(), tweetEntity.getIsValidated());
    }

    @Test
    void getValidatedTweetsByUser() {
        given(tweetRepository.findByUserIdAndIsValidated(anyString(), anyBoolean())).willReturn(Collections.singletonList(tweetEntity));
        Iterable<TweetEntity> result = tweetService.getValidatedTweetsByUser(USER_ID);
        assertTrue(result.iterator().hasNext());
        assertEquals(result.iterator().next().getId(), tweetEntity.getId());
        assertEquals(result.iterator().next().getUser().getId(), tweetEntity.getUser().getId());
        assertEquals(result.iterator().next().getUser().getName(), tweetEntity.getUser().getName());
        assertEquals(result.iterator().next().getText(), tweetEntity.getText());
        assertEquals(result.iterator().next().getLocation(), tweetEntity.getLocation());
        assertEquals(result.iterator().next().getIsValidated(), tweetEntity.getIsValidated());
    }

    @Test
    void existTweetById() {
        given(tweetRepository.existsById(anyString())).willReturn(true);
        assertTrue(tweetService.existTweetById(USER_ID));
    }

    @Test
    void saveTweet() {
        tweetService.saveTweet(mock(TweetEntity.class));
        verify(tweetRepository, atLeastOnce()).save(any(TweetEntity.class));
    }

    @Test
    void validateTweet() {
        given(tweetRepository.findById(anyString())).willReturn(Optional.of(tweetEntity));
        ResponseEntity<String> responseEntity = tweetService.validateTweet(USER_ID);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}