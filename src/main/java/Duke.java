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
    private static boolean doExit = false;
    private static final String filePath = "data.txt";
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
                        case "list":
                            list();
                            break;
                        case "done":
                            markDone(userInputArray);
                            break;
                        case "todo":
                            markToDo(userInput);
                            break;
                        case "deadline":
                            markDeadline(userInput);
                            break;
                        case "event":
                            markEvent(userInput);
                            break;
                        case "delete":
                            deleteTask(userInputArray);
                            break;
                        default:
                            lineSeparator();
                            System.out.println("Sorry that is an invalid Command ! :(");
                            lineSeparator();
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
            String output;
            System.out.println("Loaded from previous task list:");
            output = printFileContents("data.txt");
            toAppend = true; // if there is an existing file

            String[] prevList;
            prevList = output.split("\n");

            for(String task : prevList) {
                String taskType = task.substring(0,1); // gets the first character
                String isDoneInteger = task.substring(2,3);
                switch (taskType) {
                    case "T":
                        inputTodo(task, isDoneInteger);
                        break;
                    case "E":
                        inputEvent(task, isDoneInteger);
                        break;
                    case "D":
                        inputDeadline(task, isDoneInteger);
                        break;
                }
            }
            printTaskList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        lineSeparator();
    }

    private static void printTaskList() {
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList.get(i).printTask());
        }
    }

    private static void inputDeadline(String task, String isDoneInteger) {
        int indexOfSecondBar = 3;
        String taskDescriptionAndDate = task.substring(indexOfSecondBar + 1);
        int indexOfThirdBar = taskDescriptionAndDate.indexOf("|");
        String deadlineDate = taskDescriptionAndDate.substring(indexOfThirdBar + 1);
        String taskDescription = taskDescriptionAndDate.substring(0, indexOfThirdBar);
        Task newTask = new Deadline(taskDescription, deadlineDate);
        taskList.add(newTask);
        if(isDoneInteger.equals("1")) {
            newTask.markAsDone();
        }
    }

    private static void inputEvent(String task, String isDoneInteger) {
        int indexOfSecondBar = 3;
        String taskDescriptionAndDate = task.substring(indexOfSecondBar + 1);
        int indexOfThirdBar = taskDescriptionAndDate.indexOf("|");
        String eventDate = taskDescriptionAndDate.substring(indexOfThirdBar + 1);
        String taskDescription = taskDescriptionAndDate.substring(0, indexOfThirdBar);
        Task newTask = new Event(taskDescription, eventDate);
        taskList.add(newTask);
        if(isDoneInteger.equals("1")) {
            newTask.markAsDone();
        }
    }

    private static void inputTodo(String task, String isDoneInteger) {
        int indexOfSecondBar = 3;
        String taskDescription = task.substring(indexOfSecondBar + 1);
        Task newTask = new Todo(taskDescription);
        taskList.add(newTask);
        if(isDoneInteger.equals("1")) {
            newTask.markAsDone();
        }
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
            case "todo":
            case "deadline":
            case "event":
                    System.out.println("☹ OOPS!!! The description of a " + userInput + " cannot be empty.");
                    break;
            case "bye":
                doExit = true;
                goodbye();
                break;
            default:
                lineSeparator();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                lineSeparator();
        }
        lineSeparator();
    }

    private static void deleteTask(String[] userInputArray) throws IOException {
        int indexOfDeletedTask;

        indexOfDeletedTask = Integer.parseInt(userInputArray[1]) - 1;
        Task.decrementNumberOfTask();
        System.out.println("Noted. I've removed this task:\n" + "  " + taskList.get(indexOfDeletedTask).printTask());
        taskList.remove(indexOfDeletedTask);
        getNumberOfTaskMessage();

        overwritesFile();
    }

    private static void overwritesFile() throws IOException {
        toAppend = false; // overwrites
        //method to save all
        for (int i = 0; i < Task.getNumberOfTasks(); i++) {
            appendToFile(filePath, taskList.get(i).toString(), toAppend);
            toAppend = true;
        }
    }


    private static void markEvent(String userInput ) throws IOException {
        int dividePosAt = userInput.indexOf("/at");
        String description = userInput.substring(0, dividePosAt).replace("event", "");
        String eventDate = userInput.substring(dividePosAt + 3);
        Task newTask = new Event(description.trim(), eventDate.trim());
        taskList.add(newTask);
        printAddMessage(newTask);
        appendToFile(filePath, newTask.toString(), toAppend);
    }

    private static void markDeadline(String userInput) throws IOException {
        int dividePosBy = userInput.indexOf("/by");
        String description = userInput.substring(0, dividePosBy).replace("deadline", "");
        String deadlineDate = userInput.substring(dividePosBy + 3);
        Task newTask = new Deadline(description.trim(), deadlineDate.trim());
        taskList.add(newTask);
        printAddMessage(newTask);
        appendToFile(filePath, newTask.toString(), toAppend);
    }

    private static void markToDo(String userInput) throws IOException {
        String description = userInput.replace("todo", "");
        Task newTask = new Todo(description.trim());
        taskList.add(newTask);
        printAddMessage(newTask);
        appendToFile(filePath, newTask.toString(), toAppend);
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

    private static void markDone(String[] userInputArray) throws IOException {
        lineSeparator();
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

        overwritesFile();
    }

    private static void list() {
        lineSeparator();
        System.out.println("Here are the tasks in your list:");
        printTaskList();
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
