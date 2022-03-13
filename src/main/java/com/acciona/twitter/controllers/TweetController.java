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
    public Page<TweetEntity> getAllPaginated(@RequestParam(defaultValue = "0") final int page) {
        return tweetService.getAll(page);
    }

    @GetMapping("/{id}")
    public TweetEntity get(@PathVariable final String id) {
        return tweetService.get(id);
    }

    @GetMapping("/validated-tweets-user/{userId}")
    public Iterable<TweetEntity> getValidatedTweetsByUser(@PathVariable final String userId) {
        return tweetService.getValidatedTweetsByUser(userId);
    }

    @PutMapping("/validate-tweet/{id}")
    public ResponseEntity<String> validateTweet(@PathVariable final String id) {
        return tweetService.validateTweet(id);
    }
}
