package edu.upenn.cit594.util;

/**
 * Represents a state with a name and geographic coordinates.
 */
public class State {
    String name;
    double latitude;
    double longitude;

    /**
     * Constructs a new State instance.
     *
     * @param name      The name of the state.
     * @param latitude  The latitude of the state's location.
     * @param longitude The longitude of the state's location.
     */
    public State(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter Methods
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
