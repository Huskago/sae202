package fr.huskago.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FOLDER = "logs";
    private static final String LOG_FILE_PREFIX = "log_";
    private static final String LOG_FILE_EXTENSION = ".txt";
    private BufferedWriter writer;

    public Logger() {
        createLogsFolderIfNotExists();
        createLogFile();
    }

    private void createLogsFolderIfNotExists() {
        Path logsFolderPath = Paths.get(LOG_FOLDER);

        if (!Files.exists(logsFolderPath)) {
            try {
                Files.createDirectories(logsFolderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createLogFile() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        String logFileName = "src/main/resources/" + LOG_FOLDER + "/" + LOG_FILE_PREFIX + timestamp + LOG_FILE_EXTENSION;

        try {
            writer = new BufferedWriter(new FileWriter(logFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        String logEntry = String.format("[%s] %s\n", timestamp, message);

        try {
            writer.append(logEntry);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeLogger() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
