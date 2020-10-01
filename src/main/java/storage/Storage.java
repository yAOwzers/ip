package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exceptions.DukeInvalidUserInputException;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents the storage of where Duke is loading information from and saving information to.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs the storage based on a filepath to a txt file.
     * @param filePath to txt file.
     */
    public Storage(String filePath) {
            this.filePath = filePath;
    }

    /**
     * Loads the data in the text file from the file path to an assigned task.TaskList.
     * @param taskList to load data to.
     * @throws DukeInvalidUserInputException if there are any invalid inputs in the file
     * that are unable to be parsed into a Task.
     */
    public void loadTaskList(TaskList taskList) throws DukeInvalidUserInputException {
        File file = new File(this.filePath); // create a File for the given file path
        try {
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                Task loadTaskList = Task.parse(s.nextLine());
                taskList.load(loadTaskList);
            }
        } catch (FileNotFoundException e) {
            // If file is not found, a new file will be created
        }
    }

    /**
     * Saves the given task to the text file from the file path.
     * @param task to be saved into text file.
     */
    public void saveTask(Task task) {
        File file = new File(this.filePath);
        try {
                file.getParentFile().mkdir(); // create a directory
                file.createNewFile(); // create .txt file

            // checks whether the file exists
            if (file.length() > 0) {
                FileWriter writeToFile = new FileWriter(file, true);
                writeToFile.write(System.lineSeparator() +task.toTextFormat());
                writeToFile.close();
            } else {
                FileWriter writeToFile = new FileWriter(this.filePath);
                writeToFile.write(task.toTextFormat());
                writeToFile.close();
            }
        } catch (IOException e) {
            System.out.println(Ui.printUnknownError() + Ui.lineSeparator());
        }
    }

    /**
     * Overwrites and saves an entire tasklist into the text file from the file path.
     * @param taskList to be saved into the text file.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter overwriteFile = new FileWriter(this.filePath);
            if (taskList.getTotalTask() > 0) {
                overwriteFile.write(taskList.getTask(0).toTextFormat());
                overwriteFile.close();
                for (int i = 1; i < taskList.getTaskList().size(); i++) {
                    saveTask(taskList.getTask(i));
                }
            } else {
                overwriteFile.write("");
                overwriteFile.close();
            }
        } catch (IOException e) {
            System.out.println(Ui.printUnknownError() + Ui.lineSeparator());
        }
    }
    }
