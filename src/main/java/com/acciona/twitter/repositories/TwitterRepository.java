package com.acciona.twitter.repositories;

import com.acciona.twitter.entity.Twitter;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;

@RepositoryConfig(cacheName = "TwitterCache")
public interface TwitterRepository extends IgniteRepository<Twitter, Long> {

}
