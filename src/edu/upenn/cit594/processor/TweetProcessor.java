package edu.upenn.cit594.processor;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.Tweet;

import java.util.List;

public class TweetProcessor {
    private final Logger logger;

    public TweetProcessor(Logger logger) {
        this.logger = logger;
    }

    public void processTweets(List<Tweet> tweets) {
        for (Tweet tweet : tweets) {
            if (isFluTweet(tweet.getText())) {
                logger.log(tweet.getText());
            }
        }
    }

    private boolean isFluTweet(String text) {
        // logic to determine if a tweet is flu-related
        return true;
    }
}
