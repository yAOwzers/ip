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

        while(true) {
            Scanner input = new Scanner(System.in);

            String echo = input.nextLine();
            if(echo.equals("bye")){
                break;
            }

            storeUserText[count] = echo;
            lineSeparator();
            System.out.println("added: " + echo);
            lineSeparator();
            count++;
        }


        goodbye();


    }


}
