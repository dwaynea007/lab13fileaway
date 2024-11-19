import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileListMaker {
    private static List<String> itemList = new ArrayList<>();
    private static boolean isModified = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Load List");
            System.out.println("2. Save List");
            System.out.println("3. Add Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Display List");
            System.out.println("6. Create New List");
            System.out.println("7. Quit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> loadListPrompt(scanner);
                    case 2 -> saveListPrompt(scanner);
                    case 3 -> addItem(scanner);
                    case 4 -> deleteItem(scanner);
                    case 5 -> displayList();
                    case 6 -> createNewList(scanner);
                    case 7 -> running = quitProgram(scanner);
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void loadListPrompt(Scanner scanner) throws IOException {
        if (checkUnsavedChanges(scanner)) {
            System.out.print("Enter the filename to load: ");
            String filename = scanner.nextLine();
            itemList = loadList(filename);
            isModified = false;
            System.out.println("List loaded from " + filename);
        }
    }

    private static void saveListPrompt(Scanner scanner) throws IOException {
        System.out.print("Enter the filename to save to: ");
        String filename = scanner.nextLine();
        saveList(filename, itemList);
        isModified = false;
        System.out.println("List saved to " + filename);
    }

    private static void addItem(Scanner scanner) {
        System.out.print("Enter an item to add: ");
        String item = scanner.nextLine();
        itemList.add(item);
        isModified = true;
        System.out.println("Item added.");
    }

    private static void deleteItem(Scanner scanner) {
        System.out.print("Enter an item to delete: ");
        String item = scanner.nextLine();
        if (itemList.remove(item)) {
            isModified = true;
            System.out.println("Item removed.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void displayList() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("List contents:");
            itemList.forEach(System.out::println);
        }
    }

    private static void createNewList(Scanner scanner) {
        if (checkUnsavedChanges(scanner)) {
            itemList.clear();
            isModified = true;
            System.out.println("New list created.");
        }
    }

    private static boolean quitProgram(Scanner scanner) {
        if (checkUnsavedChanges(scanner)) {
            System.out.println("Exiting program. Goodbye!");
            return false;
        }
        return true;
    }

    private static boolean checkUnsavedChanges(Scanner scanner) {
        if (isModified) {
            System.out.print("You have unsaved changes. Save now? (y/n): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                try {
                    saveListPrompt(scanner);
                } catch (IOException e) {
                    System.out.println("Error saving the file: " + e.getMessage());
                }
            } else {
                System.out.println("Unsaved changes discarded.");
            }
        }
        return true;
    }

    private static List<String> loadList(String filename) throws IOException {
        return Files.readAllLines(Path.of(filename));
    }

    private static void saveList(String filename, List<String> list) throws IOException {
        Files.write(Path.of(filename), list);
    }
}
