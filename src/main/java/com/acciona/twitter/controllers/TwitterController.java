package com.acciona.twitter.controllers;

import com.acciona.twitter.entities.HashtagEntity;
import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.services.HashtagService;
import com.acciona.twitter.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @Autowired
    private HashtagService hashtagService;

    @GetMapping("/all")
    public Page<TweetEntity> getTweets(@RequestParam(defaultValue = "0") final int page) {
        return twitterService.getTweets(page);
    }

    @GetMapping("/tweet/{id}")
    public TweetEntity getTweet(@PathVariable final String id) {
        return twitterService.getTweet(id);
    }

    @GetMapping("/validated-tweets-user/{userId}")
    public Iterable<TweetEntity> getValidatedTweetsByUser(@PathVariable final String userId) {
        return twitterService.getValidatedTweetsByUser(userId);
    }

    @GetMapping("/classification/hashtags")
    public Iterable<HashtagEntity> getClassificationHashtags() {
        return hashtagService.getHashtagClassification();
    }

    @PostMapping()
    public void saveTweet(TweetEntity tweetEntity) {
        twitterService.saveTweet(tweetEntity);
    }

    @PutMapping("/validate-tweet/{id}")
    public ResponseEntity<String> validateTweet(@PathVariable final String id) {
        return twitterService.validateTweet(id);
    }
}
