package com.acciona.twitter.controllers;

import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.entities.TwitterUserEntity;
import com.acciona.twitter.services.TweetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TweetControllerTest {

    @Autowired
    private TweetController tweetController;

    @MockBean
    private TweetService tweetService;

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
        given(tweetService.getAll(0)).willReturn((tweetEntityPaged));

        Page<TweetEntity> result = tweetController.getAllPaginated(0);
        assertEquals(result.getTotalElements(), 1);
        checkTweet(result);
    }


    @Test
    void get() {
        given(tweetService.get(anyString())).willReturn(tweetEntity);

        TweetEntity result = tweetController.get("1");
        assertEquals(result.getId(), tweetEntity.getId());
        assertEquals(result.getUser().getId(), tweetEntity.getUser().getId());
        assertEquals(result.getUser().getName(), tweetEntity.getUser().getName());
        assertEquals(result.getText(), tweetEntity.getText());
        assertEquals(result.getLocation(), tweetEntity.getLocation());
        assertEquals(result.getIsValidated(), tweetEntity.getIsValidated());
    }

    @Test
    void getValidatedTweetsByUser() {
        given(tweetService.getValidatedTweetsByUser(anyString())).willReturn(Collections.singletonList(tweetEntity));

        Iterable<TweetEntity> result = tweetController.getValidatedTweetsByUser("1");
        assertTrue(result.iterator().hasNext());
        checkTweet(result);
    }

    @Test
    void validateTweet() {
        tweetController.validateTweet("1");

        verify(tweetService, atLeastOnce()).validateTweet(anyString());
    }

    private void checkTweet(final Iterable<TweetEntity> result){
        assertEquals(result.iterator().next().getId(), tweetEntity.getId());
        assertEquals(result.iterator().next().getUser().getId(), tweetEntity.getUser().getId());
        assertEquals(result.iterator().next().getUser().getName(), tweetEntity.getUser().getName());
        assertEquals(result.iterator().next().getText(), tweetEntity.getText());
        assertEquals(result.iterator().next().getLocation(), tweetEntity.getLocation());
        assertEquals(result.iterator().next().getIsValidated(), tweetEntity.getIsValidated());
    }
}