import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class Lab_12_File_Away
{
    public static void main(String[] args)
    {   //ArrayList <String>recs = new ArrayList<>();
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
             int line = 0;
             while(reader.ready())
             {
                 rec = reader.readLine();
                 line ++;

                 System.out.printf("\n %4d -%60s ", line, rec);
             }
             reader.close();
             System.out.println("\n\n Data file read!");
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

        /**File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\");
        try
            {
                    OutputStream out =
                            new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                    BufferedWriter writer =
                            new BufferedWriter(new OutputStreamWriter(out));

                    for(String rec : recs)
                    {
                        writer.write(rec, 0, rec.length());
                        writer.newLine();

                    }
                    writer.close();
                System.out.println("Data file has been written!");


            }
            catch (FileNotFoundException e)
            {
                System.out.println("File Not Found!!");
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }**/

    }
}
