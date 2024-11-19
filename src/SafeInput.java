import java.util.Scanner;

public class SafeInput {

    public static String getNonEmptyString(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt + ": ");
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String getFormattedString(Scanner scanner, String prompt, String pattern) {
        String input;
        do {
            System.out.print(prompt + ": ");
            input = scanner.nextLine().trim();
        } while (!input.matches(pattern));
        return input;
    }

    public static int getInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt + ": ");
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                if (value >= min && value <= max) {
                    return value;
                }
            } else {
                scanner.next(); // consume the invalid input
            }
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
        }
    }

    public static boolean getYNConfirm(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt + ": ");
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("n")) {
                return input.equals("y");
            }
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
        }
    }
}
