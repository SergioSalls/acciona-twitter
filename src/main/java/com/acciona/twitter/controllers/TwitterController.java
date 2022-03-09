package com.acciona.twitter.controllers;

import com.acciona.twitter.entities.TwitterEntity;
import com.acciona.twitter.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @GetMapping()
    public Iterable<TwitterEntity> getTweets() throws TwitterException {
        return twitterService.getTweets();
    }

    @PostMapping()
    public void saveTweet(TwitterEntity twitterEntity) throws TwitterException {
        twitterService.saveTweet(twitterEntity);
    }
}
