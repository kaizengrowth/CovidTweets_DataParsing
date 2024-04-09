package edu.upenn.cit594.logging;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Logger class designed to support logging messages to a configured log file.
 * This class follows a singleton pattern to ensure only one instance exists throughout the application.
 */
public class Logger {
    private static Logger instance;
    private PrintWriter printWriter;
    private String currentLogFileName;

    /**
     * Private constructor to prevent external instantiation. The constructor is
     * intentionally left empty as file configuration is handled separately through
     * the configure method.
     */
    private Logger() {
        // Constructor logic here. Since file configuration is handled by the
        // configure method, we do not open the file in the constructor.
    }

    /**
     * Provides access to the singleton Logger instance.
     *
     * @return The single instance of Logger.
     */
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Configures the logger to write to a specified log file. If the logger is already
     * configured to write to a different file, it closes the current file and opens the new one.
     *
     * @param logFileName The name of the log file to write messages to.
     */
    public void configure(String logFileName) {
        if (logFileName.equals(this.currentLogFileName)) {
            return;
        }
        close(); // Close current PrintWriter if open

        this.currentLogFileName = logFileName;

        try {
            this.printWriter = new PrintWriter(new FileWriter(logFileName, true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Logs a message to the configured log file. If the
     * message or PrintWriter is null, the message is not logged.
     *
     * @param message The message to log.
     */
    public void log(String message) {
        if (message != null && printWriter != null) {
            this.printWriter.println(message);
        }
    }

    /**
     * Safely the PrintWriter associated with this Logger instance, effectively
     * flushing and stopping any further logging to the file, and ensuring no
     * NullPointerException is thrown if the PrinterWriter is null. Called before
     * application shutdown to ensure all data is flushed to the log file.
     */
    public void close() {
        if (this.printWriter != null) {
            this.printWriter.close();
            this.printWriter = null; // Ensure the writer is marked as closed
            this.currentLogFileName = null; // Reset current file name
        }
    }
}