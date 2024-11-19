import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean moreRecords = true;

        System.out.println("Welcome to the DataSaver program!");

        while (moreRecords) {
            // Collect data from the user
            String firstName = SafeInput.getNonEmptyString(scanner, "Enter First Name");
            String lastName = SafeInput.getNonEmptyString(scanner, "Enter Last Name");
            String idNumber = SafeInput.getFormattedString(scanner, "Enter ID Number (6 digits)", "\\d{6}");
            String email = SafeInput.getNonEmptyString(scanner, "Enter Email");
            int yearOfBirth = SafeInput.getInt(scanner, "Enter Year of Birth (4 digits)", 1000, 9999);

            // Format data as a CSV record
            String record = String.format("%s, %s, %s, %s, %d", firstName, lastName, idNumber, email, yearOfBirth);
            records.add(record);

            // Ask user if they want to enter another record
            moreRecords = SafeInput.getYNConfirm(scanner, "Would you like to enter another record? (y/n)");
        }

        // Get file name from the user
        String fileName = SafeInput.getNonEmptyString(scanner, "Enter the file name (without extension)") + ".csv";

        // Write records to the specified CSV file in src directory
        try (FileWriter writer = new FileWriter("src/" + fileName)) {
            for (String record : records) {
                writer.write(record + "\n");
            }
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
