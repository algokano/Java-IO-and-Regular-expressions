import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogFileManager {
    
    private static final String LOGS_DIR = "logs/";

    public LogFileManager() {
        File directory = new File(LOGS_DIR);
        if (!directory.exists()) {
            directory.mkdirs();  
        }
    }
    
    public void createLogFile(String fileName) throws IOException {
        File logFile = new File(LOGS_DIR + fileName);
        if (logFile.createNewFile()) {
            System.out.println("Log file created: " + logFile.getName());
        } else {
            System.out.println("Log file already exists.");
        }
    }
    
    public void moveLogFileToArchive(String fileName, String archiveDir) throws IOException {
        File archiveDirectory = new File(archiveDir);
        if (!archiveDirectory.exists()) {
            archiveDirectory.mkdirs();  
        }

        Path source = Paths.get(LOGS_DIR + fileName);
        Path target = Paths.get(archiveDir + "/" + fileName);
        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Log file moved to archive.");
    }
    
    public void deleteLogFile(String fileName) {
        File logFile = new File(LOGS_DIR + fileName);
        if (logFile.delete()) {
            System.out.println("Deleted the file: " + logFile.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
    
    public void archiveLogs(String[] logFiles, String archivePath) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(archivePath))) {
            for (String logFile : logFiles) {
                File file = new File(LOGS_DIR + logFile);
                zos.putNextEntry(new ZipEntry(file.getName()));
                Files.copy(file.toPath(), zos);
                zos.closeEntry();
            }
        }
        System.out.println("Logs archived.");
    }
}
