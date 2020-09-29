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

            // check whether the file exists
            if (file.length() > 0) {
                FileWriter writeToFile = new FileWriter(file, true);
                writeToFile.write(System.lineSeparator() + task.toTextFormat());
                writeToFile.close();
            } else {
                FileWriter writeToFile = new FileWriter(file);
                writeToFile.write(task.toTextFormat());
                writeToFile.close();
            }
        } catch (IOException e) {
            System.out.println(Ui.printUnknownError());
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
            System.out.println(Ui.printUnknownError());
        }
    }

    /**
     * from previous code
     */
/*
    public static void loadFile() {
        try {
            String output;
            System.out.println("Loaded from previous task list:");
            output = printFileContents("data.txt");
            toAppend = true; // if there is an existing file

            String[] prevList;
            prevList = output.split("\n");

            for (String task : prevList) {
                String taskType = task.substring(0, 1); // gets the first character
                String isDoneInteger = task.substring(2, 3);
                switch (taskType) {
                    case "T":
                        Duke.inputTodo(task, isDoneInteger);
                        break;
                    case "E":
                        Duke.inputEvent(task, isDoneInteger);
                        break;
                    case "D":
                        Duke.inputDeadline(task, isDoneInteger);
                        break;
                }
            }
            Ui.printTaskList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Ui.lineSeparator();
    }

 */

    }
