package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileReader {
    public static String printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String output = "";
        while (s.hasNext()) {
          output = output.concat(s.nextLine()) + "\n";
        }
        return output;
    }

    public static void appendToFile(String filePath, String textToAdd, boolean toAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, toAppend);
        fw.write(textToAdd);
        fw.close(); // need to call the close() method of the FileWriter object for the writing operation to be completed.
    }

}
