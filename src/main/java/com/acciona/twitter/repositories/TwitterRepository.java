package com.acciona.twitter.repositories;

import com.acciona.twitter.entities.TwitterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends CrudRepository<TwitterEntity, Long> {

}
