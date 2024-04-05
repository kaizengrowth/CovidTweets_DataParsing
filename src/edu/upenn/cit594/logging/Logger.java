package edu.upenn.cit594.logging;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static Logger instance;
    private static PrintWriter printWriter;
    private static String message;

    private Logger() {
        try {
            printWriter = new PrintWriter(new FileWriter("log.txt", true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        if (printWriter != null) {
            printWriter.println(message);
        }
    }

    public void close() {
        if (printWriter != null) {
            printWriter.close();
        }
    }
}