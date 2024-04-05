package edu.upenn.cit594;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.Tweet;
import edu.upenn.cit594.datamanagement.TweetReader;
import edu.upenn.cit594.datamanagement.JSONTweetReader;
import edu.upenn.cit594.datamanagement.TextTweetReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Error handling: expect 3 command line arguments
        if (args.length != 3) {
            System.err.println("Usage: java Main <tweets_file> <states_file> <log_file>");
            System.exit(1);
        }

        String tweetsFile = args[0];
        TweetReader reader;

        if (tweetsFile.endsWith(".json")) {
            reader = new JSONTweetReader();
        } else if (tweetsFile.endsWith(".txt")) {
            reader = new TextTweetReader();
        } else {
            System.err.println("Invalid tweets file format.");
            System.exit(1);
            return;
        }

        Logger logger = Logger.getInstance();

        List<Tweet> tweets = reader.readTweets(tweetsFile);
        for (Tweet tweet : tweets) {
            logger.log(tweet.getText());
        }
    }
}
