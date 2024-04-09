package edu.upenn.cit594.datamanagement;
import edu.upenn.cit594.util.Tweet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A TweetReader implementation for reading tweet data from JSON files.
 */
public class JSONTweetReader implements TweetReader {

    /**
     * Reads tweets from a JSON file and constructs a list of Tweet objects.
     *
     * @param filename The path to the JSON file containing tweets.
     * @return A list of Tweet objects.
     */
    @Override
    public List<Tweet> readTweets(String filename) {
        List<Tweet> tweets = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filename));

            for (Object obj : jsonArray) {
                JSONObject tweetJson = (JSONObject) obj;
                String text = (String) tweetJson.get("text");
                JSONArray location = (JSONArray) tweetJson.get("location");
                double latitude = (double) location.get(0);
                double longitude = (double) location.get(1);

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
