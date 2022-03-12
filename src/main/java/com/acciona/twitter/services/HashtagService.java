package com.acciona.twitter.services;

import com.acciona.twitter.configurations.TwitterPropertiesConfig;
import com.acciona.twitter.entities.HashtagEntity;
import com.acciona.twitter.repositories.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HashtagService {

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private TwitterPropertiesConfig twitterProperties;

    public Iterable<HashtagEntity> getHashtagClassification(){
        return hashtagRepository.findByCountGreaterThanOrderByCountDesc(twitterProperties.getMinHashtagCount());
    }

    public void saveHashtag(final String name) {
        Optional<HashtagEntity> hashtagEntity = hashtagRepository.findById(name);

        hashtagEntity.ifPresentOrElse((he) ->
                {
                    he.setCount(he.getCount()+ 1);
                    hashtagRepository.save(he);
                },
                () -> {
                    HashtagEntity he = HashtagEntity.builder().name(name).count(1).build();
                    hashtagRepository.save(he);
                }
        );
    }

}
