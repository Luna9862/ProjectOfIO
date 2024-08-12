package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SearchOperations {
    private static final Logger logger = Logger.getLogger(SearchOperations.class.getName());

    public List<String> searchByName(String directoryPath, String fileName) {
        List<String> results = new ArrayList<>();
        File directory = new File(directoryPath);
        search(directory, fileName, results);
        logger.info("Searched for files by name: " + fileName + " in " + directoryPath);
        return results;
    }

    private void search(File directory, String fileName, List<String> results) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    search(file, fileName, results);
                } else if (file.getName().equalsIgnoreCase(fileName)) {
                    results.add(file.getAbsolutePath());
                }
            }
        }
    }

    public List<String> searchByExtension(String directoryPath, String extension) {
        List<String> results = new ArrayList<>();
        File directory = new File(directoryPath);
        searchByExtension(directory, extension, results);
        logger.info("Searched for files with extension: " + extension + " in " + directoryPath);
        return results;
    }

    private void searchByExtension(File directory, String extension, List<String> results) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchByExtension(file, extension, results);
                } else if (file.getName().endsWith(extension)) {
                    results.add(file.getAbsolutePath());
                }
            }
        }
    }
}