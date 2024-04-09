package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.util.FileUtils;

/**
 * A factory for creating the appropriate TweetReader based on the file extension.
 */
public class TweetReaderFactory {

    /**
     * Creates and returns an instance of TweetReader based on the file extension.
     *
     * @param filename The name of the file to be read.
     * @return An instance of TweetReader suitable for the file type.
     * @throws IllegalArgumentException If the file extension is not supported.
     */
    public static TweetReader getTweetReader(String filename) throws IllegalArgumentException {
        if (!FileUtils.isValidExtension(filename)) {
            throw new IllegalArgumentException("Unsupported file extension: " + filename);
        }

        if (filename.toLowerCase().endsWith(".json")) {
            return new JSONTweetReader();
        } else if (filename.toLowerCase().endsWith(".txt")) {
            return new TextTweetReader();
        } else {
            // This line should theoretically never be reached due to the isValidExtension check,
            // but it's here to satisfy the compiler's requirement for a return statement.
            throw new IllegalArgumentException("Unsupported file extension: " + filename);
        }
    }
}
