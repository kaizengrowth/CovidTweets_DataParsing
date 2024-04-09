package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.util.Tweet;
import java.util.List;

/**
 * Defines the contract for TweetReader implementations.
 * Implementations of this interface are responsible for reading tweets from a data source.
 */
public interface TweetReader {

    /**
     * Reads tweets from a specified file.
     *
     * @param filename The name of the file to read tweets from.
     * @return A list of Tweet objects read from the file.
     */
    List<Tweet> readTweets(String filename);
}