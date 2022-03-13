package com.acciona.twitter.services;

import com.acciona.twitter.configurations.TwitterPropertiesConfig;
import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private TwitterPropertiesConfig twitterProperties;

    public Page<TweetEntity> getAll(final int page) {
        Pageable pageable = PageRequest.of(page, twitterProperties.getMaxTweetPage());
        return tweetRepository.findAll(pageable);
    }

    public TweetEntity get(final String id) {
        return tweetRepository.findById(id).orElse(null);
    }

    public Iterable<TweetEntity> getValidatedTweetsByUser(final String userId) {
        return tweetRepository.findByUserIdAndIsValidated(userId, true);
    }

    public boolean existTweetById(final String id){
        return tweetRepository.existsById(id);
    }

    public void saveTweet(TweetEntity tweetEntity) {
        tweetRepository.save(tweetEntity);
    }

    public ResponseEntity<String> validateTweet(final String id) {
        Optional<TweetEntity> tweetEntityOld = tweetRepository.findById(id);

        return tweetEntityOld.map(t -> {
            TweetEntity tweetEntity = TweetEntity.builder().id(t.getId()).user(t.getUser()).location(t.getLocation()).text(t.getText()).isValidated(true).build();
            tweetRepository.save(tweetEntity);
            return response("Tweet validado correctamente", HttpStatus.OK);
        }).orElse(response("El tweet a validar no existe", HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<String> response(final String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(message, httpStatus);
    }
}
