package duke;

import java.util.Scanner;


public class Duke {
    private static Task[] taskList = new Task[100];
    private static String[] storeUserText = new String[100];
    private static boolean doExit = false;

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

            if (firstWord.equals("list")) {
                list();
            }
            else if (firstWord.equals("done")) {
                markDone(userInputArray);
            }
            else if (firstWord.equals("todo")) {
                markToDo(userInput);
            }
            else if (firstWord.equals("deadline")) {
                markDeadline(userInput);
            }
            else if (firstWord.equals("event")) {
                markEvent(userInput);
            }
            else {
                addTask(storeUserText, userInput);
            }

            } catch (DukeException e){
                errorMessage(userInput);
            }

            }

        }

    private static String getInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        return userInput;
    }

    private static String[] checkInput(String userInput) throws DukeException{
        String[] checkUserInput = userInput.split(" ");
        if(checkUserInput.length < 2) {
            throw new DukeException();
        }
        return checkUserInput;
    }

    private static void errorMessage(String userInput) {
        lineSeparator();
        switch(userInput) {
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
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        lineSeparator();
    }

    private static void markEvent(String userInput ){
        int dividePosAt = userInput.indexOf("/at");
        String description = userInput.substring(0, dividePosAt).replace("event", "");
        String eventDate = userInput.substring((dividePosAt + 3), userInput.length());
        Task newTask = new Event(description.trim(), eventDate.trim());
        taskList[Task.numberOfTasks - 1] = newTask;
        printAddMessage(newTask);
    }

    private static void markDeadline(String userInput){
        int dividePosBy = userInput.indexOf("/by");
        String description = userInput.substring(0, dividePosBy).replace("deadline", "");
        String deadlineDate = userInput.substring((dividePosBy + 3), userInput.length());
        Task newTask = new Deadline(description.trim(), deadlineDate.trim());
        taskList[Task.numberOfTasks - 1] = newTask;
        printAddMessage(newTask);
    }

    private static void markToDo(String userInput){
        String description = userInput.replace("todo", "");
        Task newTask = new Todo(description.trim());
        taskList[Task.numberOfTasks - 1] = newTask;
        printAddMessage(newTask);
    }

    public static void printAddMessage(Task newTask) {
        lineSeparator();
        System.out.print("Got it. I've added this task:\n  " + newTask.printTask());

        //If there is only one task, then task will be singular
        if(newTask.numberOfTasks == 1) {
            System.out.println("Now you have " + newTask.numberOfTasks + " task in the list.");
        }
        else {
            System.out.println("Now you have " + newTask.numberOfTasks + " tasks in the list.");
        }
        lineSeparator();
    }

    private static void addTask(String[] storeUserText, String userInput) {
        storeUserText[Task.numberOfTasks] = userInput;
        echo(userInput);
        Task addTask = new Task(userInput);
        taskList[Task.numberOfTasks] = addTask;
    }

    private static void echo(String userInput) {
        //user reply
        lineSeparator();
        System.out.println("added: " + userInput);
        lineSeparator();
    }

    private static void markDone(String[] userInputArray) {
        //check if it is a valid input eg. "done 2" and NOT "done"
        if (userInputArray.length < 2) {
            System.out.println("Invalid input!");
        }

        //due to zero indexing, we need to correct the index by deducting 1
        //eg. "done 2", index of the task in the array is 1, and NOT 2.
        int indexOfDoneTask = Integer.parseInt(userInputArray[1]) - 1;
        taskList[indexOfDoneTask].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + taskList[indexOfDoneTask].printTask());
        lineSeparator();
    }



    private static void list() {
        lineSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.numberOfTasks; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList[i].printTask());
        }
        lineSeparator();
    }

    public static void greet() {
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    public static void lineSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
