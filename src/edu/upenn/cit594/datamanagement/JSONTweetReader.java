package edu.upenn.cit594.datamanagement;
import edu.upenn.cit594.util.Tweet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONTweetReader implements TweetReader {
    public List<Tweet> readTweets(String filename) {
        List<Tweet> tweets = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filename));

            for (Object obj : jsonArray) {
                JSONObject tweetJson = (JSONObject) obj;
                String text = (String) tweetJson.get("text");
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
