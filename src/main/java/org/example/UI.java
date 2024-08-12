package org.example;

import java.awt.Desktop; //Import for opening the directory in the file explorer
import java.io.File; //Import for file and directory handling
import java.io.IOException; //Import for handling IOExceptions
import java.util.List; //Import for user input handling
import java.util.Scanner; //Import for handling lists of search results

public class UI {
    private final FileOperations fileOperations = new FileOperations(); //Handles file-related operations
    private final DirectoryOperations directoryOperations = new DirectoryOperations(); //Handles directory-related operations
    private final SearchOperations searchOperations = new SearchOperations(); //Handles search-related operations
//Method that starts the user interface and main application loop
    public void start() {
        Scanner scanner = new Scanner(System.in); //scanner for reading user input
        while (true) { //Infinite loop to keep the application running until the user decides to exit
            showMenu(); //displays the meu options to the user
            String choice = scanner.nextLine(); //Read the user's choice

            switch (choice) { //creating different switch cases for the user to select choice
                case "1":
                    System.out.print("Enter directory path: "); //Prompt for directory path
                    String dirPath = scanner.nextLine(); //Read the directory path
                    fileOperations.listFiles(dirPath); // List the contents of the directory
                    break;
                case "2":
                    handleFileCopy(scanner);
                    break;
                case "3":
                    handleFileMove(scanner);
                    break;
                case "4":
                    handleFileDelete(scanner);
                    break;
                case "5":
                    handleDirectoryCreate(scanner);
                    break;
                case "6":
                    handleDirectoryDelete(scanner);
                    break;
                case "7":
                    handleFileSearch(scanner);
                    break;
                case "8":
                    handleExtensionSearch(scanner);
                    break;
                case "9":
                    handleOpenDirectory(scanner);
                    break;
                case "0":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
//The method of displaying the menu options
    private void showMenu() {
        System.out.println("\n--- File Manager ---");
        System.out.println("1. Display Directory Details");
        System.out.println("2. Copy a File");
        System.out.println("3. Move a File");
        System.out.println("4. Delete a File");
        System.out.println("5. Create a Directory");
        System.out.println("6. Delete a Directory");
        System.out.println("7. Search File by Name");
        System.out.println("8. Search File by Extension");
        System.out.println("9. Open Directory in File Explorer");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
//This method handles copying a file
    private void handleFileCopy(Scanner scanner) {
        try {
            System.out.print("Enter source file path: ");
            String source = scanner.nextLine();
            System.out.print("Enter destination file path: ");
            String destination = scanner.nextLine();
            fileOperations.copyFile(source, destination);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }
//This method handles moving a file
    private void handleFileMove(Scanner scanner) {
        try {
            System.out.print("Enter source file path: ");
            String source = scanner.nextLine();
            System.out.print("Enter destination file path: ");
            String destination = scanner.nextLine();
            fileOperations.moveFile(source, destination);
            System.out.println("File moved successfully.");
        } catch (IOException e) {
            System.err.println("Error moving file: " + e.getMessage());
        }
    }
//Method to handle deleting file
    private void handleFileDelete(Scanner scanner) {
        try {
            System.out.print("Enter file path to delete: ");
            String filePath = scanner.nextLine();
            fileOperations.deleteFile(filePath);
            System.out.println("File deleted successfully.");
        } catch (IOException e) {
            System.err.println("Error deleting file: " + e.getMessage());
        }
    }
//Method to handle creating a directory
    private void handleDirectoryCreate(Scanner scanner) {
        try {
            System.out.print("Enter directory path to create: ");
            String dirPath = scanner.nextLine();
            directoryOperations.createDirectory(dirPath);
            System.out.println("Directory created successfully.");
        } catch (IOException e) {
            System.err.println("Error creating directory: " + e.getMessage());
        }
    }
//Method to handle deleting a directory
    private void handleDirectoryDelete(Scanner scanner) {
        try {
            System.out.print("Enter directory path to delete: ");
            String dirPath = scanner.nextLine();
            directoryOperations.deleteDirectory(dirPath);
            System.out.println("Directory deleted successfully.");
        } catch (IOException e) {
            System.err.println("Error deleting directory: " + e.getMessage());
        }
    }
//Method to handle searching for files by name
    private void handleFileSearch(Scanner scanner) {
        System.out.print("Enter directory path: ");
        String dirPath = scanner.nextLine();
        System.out.print("Enter file name to search: ");
        String fileName = scanner.nextLine();
        List<String> results = searchOperations.searchByName(dirPath, fileName);
        displaySearchResults(results);
    }
// Method to handle searching for files by extension
    private void handleExtensionSearch(Scanner scanner) {
        System.out.print("Enter directory path: ");
        String dirPath = scanner.nextLine();
        System.out.print("Enter file extension to search (e.g., .txt): ");
        String extension = scanner.nextLine();
        List<String> results = searchOperations.searchByExtension(dirPath, extension);
        displaySearchResults(results);
    }
// Method to display the search results
    private void displaySearchResults(List<String> results) {
        if (results.isEmpty()) { //check if no files were found
            System.out.println("No files found."); //Display a message if no files were found
        } else {
            System.out.println("Files found:"); //Diplsay a message if files were found
            for (String result : results) { //Iterate over the search results
                System.out.println(result); //Display each result
            }
        }
    }
//Method to handle opening a directory in the file explorer
    private void handleOpenDirectory(Scanner scanner) {
        System.out.print("Enter directory path to open: ");
        String dirPath = scanner.nextLine();
        openDirectoryInExplorer(dirPath);
    }

    private void openDirectoryInExplorer(String directoryPath) {
        try {
            File directory = new File(directoryPath);
            if (directory.exists() && directory.isDirectory()) {
                Desktop.getDesktop().open(directory);
                System.out.println("Opened directory in file explorer.");
            } else {
                System.err.println("Invalid directory path.");
            }
        } catch (IOException e) {
            System.err.println("Error opening directory: " + e.getMessage());
        }
    }
}

