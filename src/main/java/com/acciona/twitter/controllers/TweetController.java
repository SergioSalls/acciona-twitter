package com.acciona.twitter.controllers;

import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping("/all")
    public Page<TweetEntity> getTweets(@RequestParam(defaultValue = "0") final int page) {
        return tweetService.getTweets(page);
    }

    @GetMapping("/tweet/{id}")
    public TweetEntity getTweet(@PathVariable final String id) {
        return tweetService.getTweet(id);
    }

    @GetMapping("/validated-tweets-user/{userId}")
    public Iterable<TweetEntity> getValidatedTweetsByUser(@PathVariable final String userId) {
        return tweetService.getValidatedTweetsByUser(userId);
    }

    @PostMapping()
    public void saveTweet(TweetEntity tweetEntity) {
        tweetService.saveTweet(tweetEntity);
    }

    @PutMapping("/validate-tweet/{id}")
    public ResponseEntity<String> validateTweet(@PathVariable final String id) {
        return tweetService.validateTweet(id);
    }
}
