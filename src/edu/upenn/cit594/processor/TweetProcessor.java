package edu.upenn.cit594.processor;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.Tweet;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetProcessor {
    private final Logger logger;
    private static final Pattern fluPattern = Pattern.compile("(?i)(?:^|\\W)(#?flu)(?![a-z])");

    public TweetProcessor(Logger logger) {
        this.logger = logger;
    }

    public void processTweets(List<Tweet> tweets) {
        for (Tweet tweet : tweets) {
            String text = tweet.getText();
            if (isFluTweet(text)) {
                logger.log(text);
            }
        }
    }

    private boolean isFluTweet(String text) {
        Matcher matcher = fluPattern.matcher(text);
        boolean found = matcher.find();
        if (found) {
            System.out.println("Flu-related tweet found.");
            System.out.println(text);
        }
        return found;
    }
}
