package com.acciona.twitter.services;

import com.acciona.twitter.entities.TwitterEntity;
import com.acciona.twitter.repositories.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwitterService {

    @Autowired
    private TwitterRepository twitterRepository;

    public Iterable<TwitterEntity> getTweets() {
        return twitterRepository.findAll();
    }

    public void saveTweet(TwitterEntity twitterEntity) {
        twitterRepository.save(twitterEntity);
    }
}
