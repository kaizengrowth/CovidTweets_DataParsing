package edu.upenn.cit594.datamanagement;
import edu.upenn.cit594.util.Tweet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A TweetReader implementation for reading tweet data from text files.
 */
public class TextTweetReader implements TweetReader {

    /**
     * Reads tweets from a text file where each line is a tweet, and constructs a list of Tweet objects.
     *
     * @param filename The path to the text file containing tweets.
     * @return A list of Tweet objects.
     */
    @Override
    public List<Tweet> readTweets(String filename) {
        List<Tweet> tweets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                String text = parts[3];
                String[] locationParts = parts[0].replaceAll("[\\[\\]]", "").split(", ");
                double latitude = Double.parseDouble(locationParts[0]);
                double longitude = Double.parseDouble(locationParts[1]);

                Tweet tweet = new Tweet();
                tweet.setText(text);
                tweet.setLatitude(latitude);
                tweet.setLongitude(longitude);
                tweets.add(tweet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tweets;
    }
}