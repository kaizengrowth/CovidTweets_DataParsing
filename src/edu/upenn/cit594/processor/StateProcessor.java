package edu.upenn.cit594.processor;
import edu.upenn.cit594.util.State;

import java.util.List;

/**
 * Processes state information, particularly finding the closest state to a given geographic location.
 */
public class StateProcessor {
    private List<State> states;
    public StateProcessor(List<State> states) {
        this.states = states;
    }

    /**
     * Finds the closest state to the given latitude and longitude.
     *
     * @param tweetLatitude  The latitude of the tweet's location.
     * @param tweetLongitude The longitude of the tweet's location.
     * @return The name of the closest state or "Unknown" if no state is found.
     */
    public String findClosestState(double tweetLatitude, double tweetLongitude) {
        State closestState = null;
        double minDistance = Double.MAX_VALUE;

        for (State state : this.states) {
            double distance = calculateDistance(tweetLatitude, tweetLongitude, state.getLatitude(), state.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                closestState = state;
            }
        }

        return closestState != null ? closestState.getName() : "Unknown";
    }

    // Helper method to calculate distance
    private static double calculateDistance(double lat1, double long1, double lat2, double long2) {
        return Math.sqrt(Math.pow(long1 - long2, 2) + Math.pow(lat1 - lat2, 2));
    }
}
