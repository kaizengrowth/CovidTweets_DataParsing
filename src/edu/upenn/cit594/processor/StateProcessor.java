package edu.upenn.cit594.processor;
import edu.upenn.cit594.util.State;

import java.util.List;

public class StateProcessor {
    private List<State> states;
    public StateProcessor(List<State> states) {
        this.states = states;
    }

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

    private static double calculateDistance(double lat1, double long1, double lat2, double long2) {
        return Math.sqrt(Math.pow(long1 - long2, 2) + Math.pow(lat1 - lat2, 2));
    }
}
