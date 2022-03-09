package com.acciona.twitter.services;

import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.RequestToken;

import java.util.List;

@Service
public class TwitterService {

    public String getTwitter() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("VawWsHDBXgbiJDl7skF5x7OMT", "DoDDvxHB2WhMGVCpkUBW5EwiEss8jPQ3fQRKLCVtibKzIGIaj9");
        RequestToken requestToken = twitter.getOAuthRequestToken();
        AccessToken tokenAccess = new AccessToken("1499797827148468230-de4FZdi5mteNbfJEGJaZZ7ed8kdL17", "8jwzPtvndpu7CGyvKOnxIQuVhAe2B49wYh17x5rrux0Pb");

        twitter.setOAuthAccessToken(tokenAccess);
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                    status.getText());
        }
        return "PRUEBA";
    }
}
