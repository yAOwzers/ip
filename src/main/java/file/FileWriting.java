package file;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close(); // need to call the close() method of the FileWriter object for the writing operation to be completed.
    }

    // appends to the file (instead of overwriting the current content)
    // by specifying an additional boolean parameter to the constructor.
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) {
        String file2 = "temp/lines.txt"; // file name to be changed
        try {
            writeToFile(file2, "first line" + System.lineSeparator() + "second line");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
