package org.example;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class FileOperations {
    private static final Logger logger = Logger.getLogger(FileOperations.class.getName());

    public void listFiles(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.err.println("Invalid directory path.");
            return;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            for (File file : files) {
                System.out.printf("%-30s %-10d %s\n",
                        file.getName(),
                        file.length(),
                        sdf.format(file.lastModified()));
            }
        }
    }

    public void copyFile(String sourcePath, String destinationPath) throws IOException {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        logger.info("Copied file from " + sourcePath + " to " + destinationPath);
    }

    public void moveFile(String sourcePath, String destinationPath) throws IOException {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        // Move the file
        Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        logger.info("Moved file from " + sourcePath + " to " + destinationPath);

        // Check if the source directory is empty
        File sourceDirectory = source.toFile().getParentFile();
        if (sourceDirectory.list().length == 0) {
            // Do nothing, leave the directory empty
        }
    }

    public void deleteFile(String filePath) throws IOException {
        File file = new File(filePath);

        // Debugging print statements
        System.out.println("Attempting to delete file at: " + filePath);
        System.out.println("Does the file exist? " + file.exists());
        System.out.println("Is this a file? " + file.isFile());

        // Check if the file exists and is actually a file
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                throw new IOException("Failed to delete file.");
            }
        } else {
            System.err.println("The specified path is either a directory or does not exist.");
        }
    }
}