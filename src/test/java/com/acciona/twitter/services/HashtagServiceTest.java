package com.acciona.twitter.services;

import com.acciona.twitter.entities.HashtagEntity;
import com.acciona.twitter.repositories.HashtagRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class HashtagServiceTest {

    @Autowired
    private HashtagService hashtagService;

    @MockBean
    private HashtagRepository hashtagRepository;

    private HashtagEntity hashtagEntity = HashtagEntity.builder()
            .name("Prueba")
            .count(1)
            .build();

    @Test
    void getHashtags() {
        given(hashtagService.getHashtags()).willReturn(Collections.singletonList(hashtagEntity));
        Iterable<HashtagEntity> result = hashtagService.getHashtags();
        checkHashtag(result);
    }

    @Test
    void getHashtagClassification() {
        given(hashtagService.getHashtagClassification()).willReturn(Collections.singletonList(hashtagEntity));
        Iterable<HashtagEntity> result = hashtagService.getHashtagClassification();
        checkHashtag(result);
    }

    @Test
    void saveHashtagNotExist() {
        hashtagService.saveHashtag("Prueba");
        verify(hashtagRepository).save(hashtagEntity);
        assertEquals(hashtagEntity.getCount(), 1);
    }

    @Test
    void saveHashtagExist() {
        given(hashtagRepository.findById(anyString())).willReturn(Optional.of(hashtagEntity));
        hashtagService.saveHashtag("Prueba");
        verify(hashtagRepository).save(hashtagEntity);
        assertEquals(hashtagEntity.getCount(), 2);
    }

    private void checkHashtag(final Iterable<HashtagEntity> result){
        assertTrue(result.iterator().hasNext());
        assertEquals(result.iterator().next().getName(), hashtagEntity.getName());
        assertEquals(result.iterator().next().getCount(), hashtagEntity.getCount());
    }
}