package com.acciona.twitter.services;

import com.acciona.twitter.entities.LanguageEntity;
import com.acciona.twitter.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Iterable<LanguageEntity> getAllLanguages(){
        return languageRepository.findAll();
    }
}
