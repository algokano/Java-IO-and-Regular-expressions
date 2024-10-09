import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        LogFileManager logManager = new LogFileManager();

        String logFileName = "charging_station_01.log";
        String archiveDir = "archived_logs";

        try {
            logManager.createLogFile(logFileName);

            for (int i = 0; i < 4; i++) {
                System.out.println("Charging Station 01 - Log Entry " + (i + 1));
            }

            logManager.moveLogFileToArchive(logFileName, archiveDir);

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}