package com.acciona.twitter.loaders;

import com.acciona.twitter.configurations.LanguagePropertiesConfig;
import com.acciona.twitter.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/*
@DependsOn(value = "languageProperties")
@Component
public class LanguageDataLoader {

    @Autowired
    private LanguagePropertiesConfig languageProperties;

    @Autowired
    private LanguageRepository languageRepository;

    public LanguageDataLoader() {
        loadLanguageInDatabase();
    }

    private void loadLanguageInDatabase() {
        System.out.println("IIEEEEEE");
        System.out.println(languageProperties);
        //languages.forEach(language -> languageRepository.save(language));
    }
}
*/
