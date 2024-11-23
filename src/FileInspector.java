import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileInspector
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec ="";

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));


                // Variables for counters
                int line = 0;
                int word = 0;
                int charCount =0;

                System.out.println("Contents of file:");
                System.out.println("_________________");

                //Reading the file
                while(reader.ready())
                {
                    rec = reader.readLine();
                    line ++;
                    word += rec.split("\\s+").length; //Split by whitespaces to count words.
                    charCount += rec.length(); //Counting Characters in the lines

                    System.out.printf("\n %4d -%60s ", line, rec);
                }
                reader.close();

                // Print summary report
                System.out.println("\n\nSummary Report:");
                System.out.println("---------------------");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Number of Lines: " + line);
                System.out.println("Number of Words: " + word);
                System.out.println("Number of Characters: " + charCount);
                System.out.println("\nData file read successfully!");
            }
        }
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


