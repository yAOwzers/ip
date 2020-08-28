import java.util.Scanner;


public class Duke {

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void lineSeparator() {
        System.out.println("_________________________");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

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
        goodbye();
        lineSeparator();

        String[] storeUserText = new String[100];
        int count = 0;
        Task[] taskList = new Task[100];

        while (true) {
            Scanner input = new Scanner(System.in);
            String echo = input.nextLine();

            //exit
            if (echo.equals("bye")) {
                goodbye();
                break;
            }

            //List
            else if (echo.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.print((i + 1) + ". ");
                    taskList[i].printTask();
                }
            } else {
                //check for done command
                String[] checkDone = echo.split(" ");
                if (checkDone[0].equals("done")) {
                    //check if it is a valid input eg. "done 2" and NOT "done"
                    if (checkDone.length < 2) {
                        System.out.println("Invalid input!");
                    }

                    int indexOfDoneTask = Integer.parseInt(checkDone[1]) - 1;
                    taskList[indexOfDoneTask].markAsDone();
                    System.out.print("Nice! I've marked this task as done:\n");
                    taskList[indexOfDoneTask].printTask();
                } else {
                    storeUserText[count] = echo;

                    //user reply
                    lineSeparator();
                    System.out.println("added: " + echo);
                    lineSeparator();

                    Task addTask = new Task(echo);
                    taskList[count] = addTask;
                    count++;
                }
            }
        }
    }
}
