package edu.upenn.cit594.datamanagement;
import edu.upenn.cit594.util.Tweet;
import java.util.List;


// TweetReader interface with two implementations for JSON and TSV text files
public interface TweetReader {
    List<Tweet> readTweets(String filename);
}
