package com.acciona.twitter.repositories;

import com.acciona.twitter.entities.HashtagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends CrudRepository<HashtagEntity, String> {

    Iterable<HashtagEntity> findByCountGreaterThanOrderByCountDesc(Integer count);
}
