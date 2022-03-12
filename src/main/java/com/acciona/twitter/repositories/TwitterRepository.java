package com.acciona.twitter.repositories;

import com.acciona.twitter.entities.TweetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends PagingAndSortingRepository<TweetEntity, String> {

    Page<TweetEntity> findAll(Pageable pageable);

    Iterable<TweetEntity> findByUserIdAndIsValidated(String userId, Boolean isValidated);
}
