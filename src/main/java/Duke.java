import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import static file.FileReader.printFileContents;
import static file.FileReader.appendToFile;


public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static String[] storeUserText = new String[100];
    private static boolean doExit = false;
    private static String fileName = "data.txt";
    private static boolean toAppend = false;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        lineSeparator();
        greet();
        lineSeparator();
        loadFile();

        while (!doExit) {
            String userInput = getInput();

            try {
                String[] userInputArray = checkInput(userInput);

            //firstWord will decide the actions that will be done
            String firstWord = userInputArray[0];

            if (firstWord.equals("bye")) {
                doExit = true;
                lineSeparator();
                break;
            }
                switch (firstWord) {
                    case "list" -> list();
                    case "done" -> markDone(userInputArray);
                    case "todo" -> markToDo(userInput);
                    case "deadline" -> markDeadline(userInput);
                    case "event" -> markEvent(userInput);
                    case "delete" -> deleteTask(userInputArray);
                    default -> addTask(storeUserText, userInput);
                }

            } catch (DukeException e){
                errorMessage(userInput);
            } catch (NumberFormatException e) {
                exceptionErrorMessage("Number formatting Error!");
            } catch (IndexOutOfBoundsException e) {
                exceptionErrorMessage("Index out of bounds exception!");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

            toAppend = true;
        }
        }

    private static void loadFile() {
        try {
            System.out.println("Loaded from previous tasklist:");
            printFileContents("data.txt"); //file path to be changed
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        lineSeparator();
    }

    private static void exceptionErrorMessage(String s) {
        lineSeparator();
        System.out.println(s);
        lineSeparator();
    }

    private static String getInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static String[] checkInput(String userInput) throws DukeException{
        String[] checkUserInput = userInput.split(" ");
        if(checkUserInput.length < 2 && !checkUserInput[0].equals("list")) {
            throw new DukeException();
        }
        return checkUserInput;
    }

    private static void errorMessage(String userInput) {
        lineSeparator();
        switch (userInput) {
            case "todo", "deadline", "event" ->
                    System.out.println("☹ OOPS!!! The description of a " + userInput + " cannot be empty.");
            case "bye" -> {
                doExit = true;
                goodbye();
            }
            default -> System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        lineSeparator();
    }

    private static void deleteTask(String[] userInputArray) {
        int indexOfDeletedTask;

        indexOfDeletedTask = Integer.parseInt(userInputArray[1]) - 1;
        Task.decremenetNumberOfTask();
        System.out.println("Noted. I've removed this task:\n" + "  " + taskList.get(indexOfDeletedTask).printTask());
        taskList.remove(indexOfDeletedTask);
        getNumberOfTaskMessage();
    }


    private static void markEvent(String userInput ) throws IOException {
        int dividePosAt = userInput.indexOf("/at");
        String description = userInput.substring(0, dividePosAt).replace("event", "");
        String eventDate = userInput.substring((dividePosAt + 3), userInput.length());
        Task newTask = new Event(description.trim(), eventDate.trim());
        taskList.add(newTask);
        printAddMessage(newTask);
        appendToFile(fileName, newTask.toString(), toAppend);
    }

    private static void markDeadline(String userInput) throws IOException {
        int dividePosBy = userInput.indexOf("/by");
        String description = userInput.substring(0, dividePosBy).replace("deadline", "");
        String deadlineDate = userInput.substring((dividePosBy + 3), userInput.length());
        Task newTask = new Deadline(description.trim(), deadlineDate.trim());
        taskList.add(newTask);
        printAddMessage(newTask);
        appendToFile(fileName, newTask.toString(), toAppend);
    }

    private static void markToDo(String userInput) throws IOException {
        String description = userInput.replace("todo", "");
        Task newTask = new Todo(description.trim());
        taskList.add(newTask);
        printAddMessage(newTask);
        appendToFile(fileName, newTask.toString(), toAppend);
    }

    public static void printAddMessage(Task newTask) {
        lineSeparator();
        System.out.println("Got it. I've added this task:\n  " + newTask.printTask());

        getNumberOfTaskMessage();
    }

    private static void getNumberOfTaskMessage() {
        //If there is only one task, then task will be singular
        if(Task.getNumberOfTasks() == 1) {
            System.out.println("Now you have " + Task.getNumberOfTasks() + " task in the list.");
        }
        else {
            System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
        }
        lineSeparator();
    }

    private static void addTask(String[] storeUserText, String userInput) {
        storeUserText[Task.getNumberOfTasks()] = userInput;
        echo(userInput);
        Task addTask = new Todo(userInput);
        taskList.add(addTask);
    }

    private static void echo(String userInput) {
        //user reply
        lineSeparator();
        System.out.println("added: " + userInput);
        lineSeparator();
    }

    private static void markDone(String[] userInputArray) throws IOException {
        //check if it is a valid input eg. "done 2" and NOT "done"
        if (userInputArray.length < 2) {
            System.out.println("Invalid input!");
        }

        //due to zero indexing, we need to correct the index by deducting 1
        //eg. "done 2", index of the task in the array is 1, and NOT 2.
        int indexOfDoneTask = Integer.parseInt(userInputArray[1]) - 1;
        // have a method to save all **

        taskList.get(indexOfDoneTask).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(indexOfDoneTask).printTask());
        lineSeparator();

        toAppend = false; // overwrites
        //method to save all
        for( Task task: taskList) {
            appendToFile(fileName, task.toString(), toAppend);
            toAppend = true;
        }
    }

    private static void list() {
        lineSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList.get(i).printTask());
        }
        lineSeparator();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void lineSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
