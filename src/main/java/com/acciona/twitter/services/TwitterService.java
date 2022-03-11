package com.acciona.twitter.services;

import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.repositories.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.Optional;

@Service
public class TwitterService {

    @Autowired
    private TwitterRepository twitterRepository;

    public Iterable<TweetEntity> getTweets() {
        return twitterRepository.findAll();
    }

    public void saveTweet(TweetEntity tweetEntity) {
        if(!twitterRepository.existsById(tweetEntity.getId())){
            twitterRepository.save(tweetEntity);
        }
    }

    public void validateTweet(final String id) throws TwitterException {
        Optional<TweetEntity> tweetEntity = twitterRepository.findById(id);

        TweetEntity tweetEntityToSave = tweetEntity.map(t ->
                TweetEntity.builder().id(t.getId()).user(t.getUser()).location(t.getLocation()).text(t.getText()).isValidated(true).build()
        ).orElseThrow(()-> new TwitterException("El tweet que se trata de validar no existe"));

        twitterRepository.save(tweetEntityToSave);
    }
}
