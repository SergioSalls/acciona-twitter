package com.acciona.twitter.services;

import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.repositories.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TwitterService {

    @Autowired
    private TwitterRepository twitterRepository;

    public Page<TweetEntity> getTweets(final int page) {
        Pageable pageable = PageRequest.of(page, 100);
        return twitterRepository.findAll(pageable);
    }

    public TweetEntity getTweet(final String id) {
        return twitterRepository.findById(id).orElse(null);
    }

    public Iterable<TweetEntity> getValidatedTweetsByUser(final String userId) {
        return twitterRepository.findByUserIdAndIsValidated(userId, true);
    }

    public boolean existTweetById(final String id){
        return twitterRepository.existsById(id);
    }

    public void saveTweet(TweetEntity tweetEntity) {
        twitterRepository.save(tweetEntity);
    }

    public ResponseEntity<String> validateTweet(final String id) {
        Optional<TweetEntity> tweetEntityOld = twitterRepository.findById(id);

        return tweetEntityOld.map(t -> {
            TweetEntity tweetEntity = TweetEntity.builder().id(t.getId()).user(t.getUser()).location(t.getLocation()).text(t.getText()).isValidated(true).build();
            twitterRepository.save(tweetEntity);
            return new ResponseEntity<>("Tweet validado correctamente", HttpStatus.OK);
        }).orElse(new ResponseEntity<>("El tweet a validar no existe", HttpStatus.NOT_FOUND));
    }
}
