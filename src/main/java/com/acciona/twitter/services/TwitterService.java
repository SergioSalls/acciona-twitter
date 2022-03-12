package com.acciona.twitter.services;

import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.repositories.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;
import java.util.Optional;

@Service
public class TwitterService {

    @Autowired
    private TwitterRepository twitterRepository;

    public Page<TweetEntity> getTweets(final int page) {
        Pageable pageable = PageRequest.of(page, 100);
        return twitterRepository.findAll(pageable);
    }

    public Iterable<TweetEntity> getValidatedTweetsByUser(final Long userId) {
        return twitterRepository.findByUserIdAndIsValidated(userId, true);
    }

    public boolean existTweetById(final String id){
        return twitterRepository.existsById(id);
    }

    public void saveTweet(TweetEntity tweetEntity) {
        twitterRepository.save(tweetEntity);
    }

    public void validateTweet(final String id) throws TwitterException {
        Optional<TweetEntity> tweetEntity = twitterRepository.findById(id);

        TweetEntity tweetEntityToSave = tweetEntity.map(t ->
                TweetEntity.builder().id(t.getId()).user(t.getUser()).location(t.getLocation()).text(t.getText()).isValidated(true).build()
        ).orElseThrow(()-> new TwitterException("El tweet que se trata de validar no existe"));

        twitterRepository.save(tweetEntityToSave);
    }
}
