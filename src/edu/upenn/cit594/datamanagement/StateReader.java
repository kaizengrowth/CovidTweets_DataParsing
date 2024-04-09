package edu.upenn.cit594.datamanagement;
import edu.upenn.cit594.util.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class StateReader {
    private String filePath;

    public StateReader(String filePath) {
        this.filePath = filePath;
    }

    public List<State> readStates() {
        List<State> states = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[0].trim();
                    double latitude = Double.parseDouble(parts[1].trim());
                    double longitude = Double.parseDouble(parts[2].trim());
                    states.add(new State(name, latitude, longitude));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return states;
    }
}
