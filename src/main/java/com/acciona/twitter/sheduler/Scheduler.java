package com.acciona.twitter.sheduler;

import com.acciona.twitter.configurations.LanguagePropertiesConfig;
import com.acciona.twitter.entities.TwitterEntity;
import com.acciona.twitter.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.*;
import java.util.concurrent.TimeUnit;

@Component
public class Scheduler {

    @Autowired
    private TwitterService twitterService;

    @Autowired
    private LanguagePropertiesConfig languageProperties;

    @Scheduled(fixedRate = 60, timeUnit = TimeUnit.SECONDS)
    public void getTweets() throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        System.out.println(languageProperties);

        //System.out.println(twitter.searchUsers("Noticias", 10));
        Paging page = new Paging(1,2);
        ResponseList<Status> timelines = twitter.getUserTimeline("elmundoes", page);

        System.out.println(timelines.size());
        saveTimeLineAccordingToFollowers(timelines);
    }

    private void saveTimeLineAccordingToFollowers(ResponseList<Status> timelines){
        timelines.forEach(timeline -> {
            System.out.println(timeline.getUser().getId());
            System.out.println(timeline.getUser().getName());
            System.out.println(timeline.getUser().getLocation());
            System.out.println(timeline.getText());
            System.out.println(timeline.getUser().isVerified());
            System.out.println(timeline.getUser().getFollowersCount());
            TwitterEntity twitterEntity = TwitterEntity.builder()
                    .id(timeline.getUser().getId())
                    .name(timeline.getUser().getName())
                    .location(timeline.getUser().getLocation())
                    .text(timeline.getText())
                    .isVerified(timeline.getUser().isVerified()).build();
            twitterService.saveTweet(twitterEntity);
        });
    }
}

