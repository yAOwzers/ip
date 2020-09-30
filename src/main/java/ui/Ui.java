package ui;

import exceptions.DukeException;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User Interface of Duke which manages all text output
 */
public class Ui {
    private final Scanner userInput;

    public Ui() {
        this.userInput = new Scanner(System.in);
    }

    /**
     * Generates and prints greeting message upon launching of Duke
     * @return
     */
    public static String greet() {
        String hello = "Hello from\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String offer = "HOI there! I'm Duke\nWhat can I do for you?";

        return hello + logo + offer + lineSeparator();
    }

    /**
     * Generates and prints Duke error message upon catching them
     * @param e Duke exception
     */
    public String showDukeError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Prints each task in the input tasklist in a labelled and ordered list view.
     * Generates and prints a message when input tasklist is empty
     * @param taskList to be printed as an indexed list
     * essentially showTaskList
     */
    public String printTaskList(ArrayList<Task> taskList) {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                output = output + (i + 1) + ". " + taskList.get(i).printTask();
            } else {
                output = output + (i + 1) + ". " + taskList.get(i).printTask() + "\n";
            }
        }

            String getListMessage = "Here are the tasks in your list:";
            String emptyListMessage = "Sorry, your tasks list seems to be empty!";
            if (taskList.size() < 1) {
                return emptyListMessage + lineSeparator();
            } else {
                return getListMessage + "\n" + output + lineSeparator();
            }
    }

    /**
     * Prints a message which displays the total number of tasks in a tasklist.
     * @param i the total number of task in a tasklist.
     */
    public String getNumberOfTaskMessage(int i) {
        //If there is only one task, then task will be singular
        if(Task.getNumberOfTasks() == 1) {
           return "Now you have " + i + " task in the list." + lineSeparator();
        }
        else {
            return "Now you have " + i + " tasks in the list." + lineSeparator();
        }
    }

    /**
     * Generates and prints the exit message upon exiting Duke.
     */
    public String showExit() {
        return "GuuuudBYE. Hope to see you again soon!";
    }

    /**
     * Generates and prints the task that has been added to the list.
     * @param newTask that is added to the tasklist.
     */
    public String printAddMessage(Task newTask) {
        String addMessage = "Got it. I've added this task:\n  ";
        return addMessage + newTask.printTask();
    }

    /**
     * Generates and prints the done message when a task is being marked as done.
     * @param task that is being marked as done.
     */
    public String showMarkDone(Task task) {
        String markDoneMessage = "NOICE! I've marked the following task as done:";
        String taskDetails = task.printTask();
        return markDoneMessage + "\n  " + taskDetails + lineSeparator();
    }

    /**
     * Generates and prints the delete message when a task is being deleted.
     * @param task that is being deleted.
     */
    public String showDelete(Task task) {
        String deleteMessage = "Okay sure, the following task has been deleted from your list:";
        String taskDetails = task.printTask();
        return deleteMessage + "\n  " + taskDetails + "\n";
    }

    /**
     * Generates and prints the unknown error message upon encountering an unidentifiable error.
     */
    public static String printUnknownError() {
        return " hmmm... error detected that is unknown.";
    }

    public String getUserInput() {
        return this.userInput.nextLine();
    }

    public static String lineSeparator() {
        return "\n-------------------------------------------------------------\n";
    }
}
