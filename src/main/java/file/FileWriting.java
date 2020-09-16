package file;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {



    // appends to the file (instead of overwriting the current content)
    // by specifying an additional boolean parameter to the constructor.
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
