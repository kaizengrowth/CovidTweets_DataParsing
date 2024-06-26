package edu.upenn.cit594.util;

/**
 * Represents a tweet, containing the text of the tweet and its geographic location.
 */
public class Tweet {
    private String text;
    private String date;
    private double latitude;
    private double longitude;

    // Getter Methods
    public String getText() {
        return this.text;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }


    // Setter Methods
    public void setText(String text) {
        this.text = text;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
