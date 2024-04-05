package edu.upenn.cit594.datamanagement;
import edu.upenn.cit594.util.Tweet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TextTweetReader implements TweetReader {
    public List<Tweet> readTweets(String filename) {
        List<Tweet> tweets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                String text = parts[3]; // just reading text for now, which is the 4th part in the TSV line
                Tweet tweet = new Tweet();
                tweet.setText(text);
                tweets.add(tweet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tweets;
    }
}