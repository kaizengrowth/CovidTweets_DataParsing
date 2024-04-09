package edu.upenn.cit594.processor;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.Tweet;
import edu.upenn.cit594.util.State;
import edu.upenn.cit594.processor.StateProcessor;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Processes tweets to analyze flu-related tweets and assigns them to states based on location.
 */
public class TweetProcessor {
    private final Logger logger;
    private final StateProcessor stateProcessor;
    private List<State> states;
    private final Map<String, Integer> fluTweetCounts = new HashMap<>();

    public TweetProcessor(Logger logger, List<State> states) {
        this.logger = logger;
        this.stateProcessor = new StateProcessor(states);
    }

    /**
     * Processes a list of tweets, filtering for flu-related tweets and logging their state distribution.
     *
     * @param tweets The list of tweets to process.
     */
    public void processTweets(List<Tweet> tweets) {

        for (Tweet tweet : tweets) {
            String text = tweet.getText();
            if (isFluTweet(text)) {
                String closestStateName = stateProcessor.findClosestState(tweet.getLatitude(), tweet.getLongitude());
                if (!closestStateName.equals("Unknown")) {
                    fluTweetCounts.put(closestStateName, fluTweetCounts.getOrDefault(closestStateName, 0) + 1);
                    logger.log(closestStateName + "\t" + text);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : fluTweetCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());

        }
    }

    /**
     * Determines if a given tweet text is related to the flu.
     *
     * @param text The text of the tweet.
     * @return true if the tweet is flu-related, false otherwise.
     */
    private boolean isFluTweet(String text) {
        Pattern fluPattern = Pattern.compile("(?i)(?:^|\\b|\\s|#)flu($|\\b|[^a-zA-Z])");
        Matcher matcher = fluPattern.matcher(text);
        return matcher.find();
    }
}
