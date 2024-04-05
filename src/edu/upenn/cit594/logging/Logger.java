package edu.upenn.cit594.logging;

public class Logger {
    private static Logger instance;

    private Logger() {}

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        // Implementation to log messages to a file
    }
}