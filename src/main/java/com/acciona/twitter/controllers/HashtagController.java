package com.acciona.twitter.controllers;

import com.acciona.twitter.entities.HashtagEntity;
import com.acciona.twitter.services.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hashtag")
public class HashtagController {

    @Autowired
    private HashtagService hashtagService;

    @GetMapping("/all")
    public Iterable<HashtagEntity> getAll() {
        return hashtagService.getHashtags();
    }

    @GetMapping("/classification/hashtags")
    public Iterable<HashtagEntity> getClassificationHashtags() {
        return hashtagService.getHashtagClassification();
    }
}
