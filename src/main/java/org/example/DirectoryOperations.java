package org.example;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class DirectoryOperations {
    private static final Logger logger = Logger.getLogger(DirectoryOperations.class.getName());

    public void createDirectory(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        Files.createDirectories(path);
        logger.info("Created directory at " + directoryPath);
    }

    public void deleteDirectory(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        Files.walk(path)
                .map(Path::toFile)
                .forEach(File::delete);
        logger.info("Deleted directory at " + directoryPath);
    }
}
