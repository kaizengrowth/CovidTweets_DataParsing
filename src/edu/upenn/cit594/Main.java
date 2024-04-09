package edu.upenn.cit594;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.TweetProcessor;
import edu.upenn.cit594.processor.StateProcessor;
import edu.upenn.cit594.util.Tweet;
import edu.upenn.cit594.util.State;
import edu.upenn.cit594.datamanagement.TweetReader;
import edu.upenn.cit594.datamanagement.JSONTweetReader;
import edu.upenn.cit594.datamanagement.TextTweetReader;
import edu.upenn.cit594.datamanagement.StateReader;

import java.util.List;

public class Main {
    public static <tweet> void main(String[] args) {
        // Error handling: expect 3 command line arguments
        if (args.length != 3) {
            System.err.println("Usage: java Main <tweets_file> <states_file> <log_file>");
            System.exit(1);
        }

        String tweetsFile = args[0];
        String statesFile = args[1];
        String logFile = args[2];

        TweetReader reader = getTweetReader(tweetsFile);
        Logger logger = Logger.getInstance();
        StateReader stateReader = new StateReader(statesFile);
        List<State> states = stateReader.readStates();

        StateProcessor stateProcessor = new StateProcessor(states);
        TweetProcessor processor = new TweetProcessor(logger, states);

        List<Tweet> tweets = reader.readTweets(tweetsFile);
        processor.processTweets(tweets);

    }

    private static TweetReader getTweetReader(String filename) {
        if (filename.endsWith(".json")) {
            return new JSONTweetReader();
        } else if (filename.endsWith(".txt")) {
            return new TextTweetReader();
        } else {
            throw new IllegalArgumentException("Invalid tweets file format.");
        }
    }
}
