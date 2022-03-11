package com.acciona.twitter.sheduler;

import com.acciona.twitter.configurations.TwitterPropertiesConfig;
import com.acciona.twitter.entities.TweetEntity;
import com.acciona.twitter.entities.TwitterUserEntity;
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
    private TwitterPropertiesConfig twitterProperties;

    @Scheduled(fixedRateString = "${twitter.cron.time.seconds}", timeUnit = TimeUnit.SECONDS)
    public void getTweets() throws TwitterException {
        Paging page = new Paging(1,1);
        Twitter twitter = new TwitterFactory().getInstance();

        System.out.println(twitter.getMentionsTimeline());
        ResponseList<Status> timelines = twitter.getUserTimeline("elmundoes", page);

        System.out.println("------------------------------");
        saveTimeLineAccordingToFollowersAndLanguages(timelines);
    }

    private void saveTimeLineAccordingToFollowersAndLanguages(ResponseList<Status> timelines){
        timelines.forEach(timeline -> {
            if(timeline.getUser().getFollowersCount() > twitterProperties.getMinfollowers()
                    && twitterProperties.getLanguages().contains(timeline.getLang())){
                twitterService.saveTweet(twitterEntity(timeline));
                System.out.println(timeline.getId());
                System.out.println(timeline.getUser().getName());
                System.out.println(timeline.getText());
                System.out.println(timeline.getUser().getLocation());
                System.out.println(timeline.getUser().getFollowersCount());
                System.out.println("--------------------------------------");
            }
            else {
                System.out.println("No cumple con los requisitos mínimos para ser guardado: ");
                System.out.println(timeline.getId());
                System.out.println(timeline.getUser().getName());
                System.out.println(timeline.getText());
                System.out.println(timeline.getUser().getLocation());
                System.out.println(timeline.getUser().getFollowersCount());
            }
        });
    }

    private TweetEntity twitterEntity(Status timeline) {
        TwitterUserEntity twitterUserEntity = TwitterUserEntity.builder()
                .id(timeline.getUser().getId())
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
}

