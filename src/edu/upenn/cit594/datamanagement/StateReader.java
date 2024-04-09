package edu.upenn.cit594.datamanagement;
import edu.upenn.cit594.util.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads state information from a CSV file and constructs a list of State objects.
 */
public class StateReader {
    private String filePath;

    /**
     * Constructs a StateReader with a specified file path.
     *
     * @param filePath The path to the CSV file containing state information.
     */
    public StateReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads states from the file and returns them as a list of State objects.
     *
     * @return A list of State objects read from the file.
     */
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
