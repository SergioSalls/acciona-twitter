package com.acciona.twitter.sheduler;

import com.acciona.twitter.configurations.TwitterPropertiesConfig;
import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.entities.TwitterUserEntity;
import com.acciona.twitter.services.HashtagService;
import com.acciona.twitter.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Scheduler {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private TwitterPropertiesConfig twitterProperties;

    @Scheduled(fixedRateString = "${twitter.cron.seconds}", timeUnit = TimeUnit.SECONDS)
    public void getTweets() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();

        for(String criteria: twitterProperties.getUsersToSearch()) {
            Query query = new Query(criteria);
            query.setCount(twitterProperties.getQueryCount());
            query.setResultType(Query.ResultType.recent);

            QueryResult queryResult = twitter.search(query);

            saveTimeLineAccordingToFollowersAndLanguages(queryResult.getTweets());
        }
    }

    private void saveTimeLineAccordingToFollowersAndLanguages(List<Status> timelines){
        timelines.forEach(timeline -> {
            if(canSaveTweetEntity(timeline)){
                tweetService.saveTweet(twitterEntity(timeline));

                List<HashtagEntity> hashtagEntities = Arrays.asList(timeline.getHashtagEntities());
                hashtagEntities.forEach(he -> hashtagService.saveHashtag(he.getText()));
            }
        });
    }

    private TweetEntity twitterEntity(final Status timeline) {
        TwitterUserEntity twitterUserEntity = TwitterUserEntity.builder()
                .id(String.valueOf(timeline.getUser().getId()))
                .name(timeline.getUser().getName())
                .build();
        return TweetEntity.builder()
                .id(String.valueOf(timeline.getId()))
                .user(twitterUserEntity)
                .location(timeline.getUser().getLocation())
                .text(timeline.getText())
                .isValidated(false)
                .build();
    }

    private Boolean canSaveTweetEntity(final Status timeline){
        return !tweetService.existTweetById(String.valueOf(timeline.getId()))
                && timeline.getUser().getFollowersCount() > twitterProperties.getMinFollowers()
                && twitterProperties.getLanguages().contains(timeline.getLang());
    }
}

