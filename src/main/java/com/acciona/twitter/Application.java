package com.acciona.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import twitter4j.*;

@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws TwitterException, InterruptedException {

        /*Twitter twitter = new TwitterFactory().getInstance();
        User usuario = twitter.verifyCredentials();

        ArrayList<Status> statuses = new ArrayList<>();
        int pageno = 1;
        while(true) {
            try {
                System.out.println("getting tweets");
                int size = statuses.size(); // actual tweets count we got
                Paging page = new Paging(pageno, 1);
                statuses.addAll(twitter.getUserTimeline("elmundoes", page));
                System.out.println(statuses.get(0).getUser().getName());
                System.out.println(statuses.get(0).getUser().getLocation());
                System.out.println(statuses.get(0).getText());
                System.out.println(statuses.get(0).getUser().isVerified());
                System.out.println(statuses.get(0).getUser().getFollowersCount());
                System.out.println("total got : " + statuses.size());
                if (statuses.size() == size) { break; } // we did not get new tweets so we have done the job
                pageno++;
                sleep(1000); // 900 rqt / 15 mn <=> 1 rqt/s
            }
            catch (TwitterException e) {
                System.out.println(e.getErrorMessage());
            }
        }*/

        SpringApplication.run(Application.class, args);
    }
}