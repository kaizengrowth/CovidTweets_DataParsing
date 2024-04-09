package edu.upenn.cit594;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.TweetProcessor;
import edu.upenn.cit594.processor.StateProcessor;
import edu.upenn.cit594.datamanagement.TweetReader;
import edu.upenn.cit594.datamanagement.TweetReaderFactory;
import edu.upenn.cit594.datamanagement.StateReader;
import edu.upenn.cit594.util.Tweet;
import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.FileUtils;

import java.util.List;

/**
 * The Main class serves as the entry point for the CDC tweet analysis program.
 * It handles initial argument checking, file validation, and initializes the program components.
 */
public class Main {

    /**
     * The main method runs the tweet analysis program.
     *
     * @param args Command line arguments: tweets file, states file, and log file.
     */
    public static <tweet> void main(String[] args) {
        // Error handling: expect 3 command line arguments
        if (args.length != 3) {
            System.err.println("Usage: java Main <tweets_file> <states_file> <log_file>");
            System.exit(1);
        }

        String tweetsFile = args[0];
        String statesFile = args[1];
        String logFile = args[2];

        // Check for valid file extensions
        if (!FileUtils.isValidExtension(tweetsFile)) {
            System.err.println("Error: Tweets file must have a .json or .txt extension.");
            System.exit(1);
        }

        // Validate file existence
        if (!FileUtils.validateFileExistence(tweetsFile) || !FileUtils.validateFileExistence(statesFile)) {
            System.err.println("Error: One or more specified files do not exist or cannot be opened.");
            System.exit(1);
        }

        try {
            // Initialize logging
            Logger logger = Logger.getInstance(logFile);

            // Initialize data readers and processors
            TweetReader reader = TweetReaderFactory.getTweetReader(tweetsFile);
            StateReader stateReader = new StateReader(statesFile);

            List<Tweet> tweets = reader.readTweets(tweetsFile);
            List<State> states = stateReader.readStates();

            TweetProcessor processor = new TweetProcessor(logger, states);

            // Process tweets
            processor.processTweets(tweets);
        } catch (Exception e) {
            System.err.println("Error initializing the application: " + e.getMessage());
            System.exit(1);
        }
    }
}
