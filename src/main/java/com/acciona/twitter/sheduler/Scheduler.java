package com.acciona.twitter.sheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.*;
import java.util.concurrent.TimeUnit;

@Component
public class Scheduler {

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void getTweets() throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();

        //System.out.println(twitter.searchUsers("Noticias", 10));
        Paging page = new Paging(1,5);
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
        });
    }
}

