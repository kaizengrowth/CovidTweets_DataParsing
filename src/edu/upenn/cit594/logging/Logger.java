package edu.upenn.cit594.logging;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    private static final Map<String, Logger> instances = new HashMap<>();
    private static PrintWriter printWriter;

    private Logger(String logFileName) {
        try {
            printWriter = new PrintWriter(new FileWriter(logFileName, true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Logger getInstance(String logFileName) {
        if (!instances.containsKey(logFileName)) {
            instances.put(logFileName, new Logger(logFileName));
        }
        return instances.get(logFileName);
    }

    public void log(String message) {
        if (message != null && printWriter != null) {
            printWriter.println(message);
        }
    }

    public void close() {
        if (printWriter != null) {
            printWriter.close();
        }
    }
}