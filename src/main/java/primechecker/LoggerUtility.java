package primechecker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtility {
    private static final String LOG_FILE_PATH = "application.log";

    public static void logInfo(String message) {
        log("INFO", message);
    }

    public static void logError(String message) {
        log("ERROR", message);
    }

    public static void logDebug(String message) {
        log("DEBUG", message);
    }

    /**
     * Handles logging to application.log
     */
    private static void log(String level, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String logMessage = String.format("%s - %s - %s", formattedDateTime, level, message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
