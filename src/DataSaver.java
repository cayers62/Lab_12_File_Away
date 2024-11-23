import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.Files.newOutputStream;
import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver
{

    public static void main(String[] args)
    {  Scanner in = new Scanner(System.in);

      //Beginning Array records.
        ArrayList<String> recs = new ArrayList<>();
        int idCounter = 1;

        // Loop to collect user data
        boolean addMore = true;

        while (addMore) {
            System.out.println("Enter the following details to create a new record:");

            // Collecting user input
            String firstName = SafeInput.getNonZeroLengthString(in, "First Name");
            String lastName = SafeInput.getNonZeroLengthString(in, "Last Name");
            String email = SafeInput.getNonZeroLengthString(in, "Email");
            int yearOfBirth = SafeInput.getInt(in, "Year of Birth (e.g., 1978)");

            // Generate ID (6-digit padded with zeros)
            String idNumber = String.format("%06d", idCounter++);

            // Create a CSV record
            String record = String.format("%s, %s, %s, %s, %d", firstName, lastName, idNumber, email, yearOfBirth);
            recs.add(record);

            // Ask if the user wants to add more records
            addMore = SafeInput.getYNConfirm(in, "Do you want to add another record?");
        }

        // Ask for file name
        String fileName = SafeInput.getNonZeroLengthString(in, "Enter the file name (without extension)");
        fileName += ".csv";


        //File writer
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath());


        try(BufferedWriter writer = Files.newBufferedWriter(file, CREATE)) {

            for (String rec : recs) {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }

            writer.close();
            System.out.println("Data file has been written!");
        }


        // Exception catching loops
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}