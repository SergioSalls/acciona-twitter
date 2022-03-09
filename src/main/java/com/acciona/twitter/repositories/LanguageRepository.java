package com.acciona.twitter.repositories;

import com.acciona.twitter.entities.LanguageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<LanguageEntity, String> {

}
