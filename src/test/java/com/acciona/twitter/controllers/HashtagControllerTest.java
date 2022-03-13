package com.acciona.twitter.controllers;

import com.acciona.twitter.entities.HashtagEntity;
import com.acciona.twitter.services.HashtagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class HashtagControllerTest {

    @Autowired
    private HashtagController hashtagController;

    @MockBean
    private HashtagService hashtagService;

    private HashtagEntity hashtagEntity = HashtagEntity.builder()
            .name("Prueba")
            .count(1)
            .build();

    @Test
    void getAll() {
        given(hashtagService.getHashtags()).willReturn(Collections.singletonList(hashtagEntity));
        Iterable<HashtagEntity> result = hashtagController.getAll();
        checkHashtag(result);
    }

    @Test
    void getClassificationHashtags() {
        given(hashtagService.getHashtagClassification()).willReturn(Collections.singletonList(hashtagEntity));
        Iterable<HashtagEntity> result = hashtagController.getClassificationHashtags();
        checkHashtag(result);
    }

    private void checkHashtag(final Iterable<HashtagEntity> result){
        assertTrue(result.iterator().hasNext());
        assertEquals(result.iterator().next().getName(), hashtagEntity.getName());
        assertEquals(result.iterator().next().getCount(), hashtagEntity.getCount());
    }
}