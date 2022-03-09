package com.acciona.twitter.controllers;

import com.acciona.twitter.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
@RequestMapping("/api")
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @GetMapping()
    public String sayHello() throws TwitterException {
        return twitterService.getTwitter();
    }
}
