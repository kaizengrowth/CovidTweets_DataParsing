package edu.upenn.cit594.ui;

public class UserInterface {

    /**
     * Prints the usage information for the application.
     */
    public void printUsage() {
        System.err.println("Usage: java Main <tweets_file> <states_file> <log_file>");
    }

    /**
     * Prints a specified error message.
     *
     * @param message The error message to print.
     */
    public void printErrorMessage(String message) {
        System.err.println(message);
    }

    /**
     * Prints a generic initialization error message with a specific cause.
     *
     * @param cause The cause of the initialization error.
     */
    public void printInitializationError(String cause) {
        System.err.println("Error initializing the application: " + cause);
    }
}