package com.acciona.twitter.controllers;

import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @GetMapping()
    public Iterable<TweetEntity> getTweets() throws TwitterException {
        return twitterService.getTweets();
    }

    @PostMapping()
    public void saveTweet(TweetEntity tweetEntity) throws TwitterException {
        twitterService.saveTweet(tweetEntity);
    }

    @PutMapping("/validate-tweet/{id}")
    public void validateTweet(@PathVariable final String id) throws TwitterException {
        twitterService.validateTweet(id);
    }
}
