package edu.upenn.cit594.util;

import java.io.File;

/**
 * Utility class for file-related operations.
 */
public class FileUtils {

    /**
     * Validates if the specified file exists and can be read.
     *
     * @param filename The name of the file to check.
     * @return true if the file exists and can be read, false otherwise.
     */
    public static boolean validateFileExistence(String filename) {
        File file = new File(filename);
        return file.exists() && file.canRead();
    }

    /**
     * Checks if the file has a valid extension for processing.
     *
     * @param filename The filename to check.
     * @return true if the file extension is valid, false otherwise.
     */
    public static boolean isValidExtension(String filename) {
        return filename.toLowerCase().endsWith(".json") || filename.toLowerCase().endsWith(".txt");
    }
}