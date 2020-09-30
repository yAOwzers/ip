package task;

import exceptions.DukeInvalidUserInputException;

/**
 * A specific type of task that contains a description of a task and a specific date and time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a deadline task.
     * @param description of task.
     * @param by of deadline task.
     * @throws DukeInvalidUserInputException when date time is incorrect format.
     */
    public Deadline(String description, String by) throws DukeInvalidUserInputException {
        super(description);
        this.by = by;
    }

    public String printTask() {
        return "[" + getType() + "]" + super.printStatus() + " " + description + " (by: " + by + ")";
    }

    public static String getType(){
        return "D";
    }

    /**
     * Converts the Deadline task into a string to be saved into a text file.
     * @return Deadline task in the form of a string.
     */
    public String toTextFormat() {
        return "D | " + super.toTextFormat() + " | " + by;
    }

    /**
     * Parses a given string array into a Deadline task.
     * @param txtArray to be parsed into a Deadline task.
     * @return Deadline task based on input string array.
     * @throws DukeInvalidUserInputException when an invalid date and time format is found in the input string array.
     */
    public static Deadline parse(String[] txtArray) throws DukeInvalidUserInputException {
        String isDoneInteger = txtArray[1].trim();
        String description = txtArray[2].trim();
        String dateTime = txtArray[3].trim();
        int indexOfDateTime = dateTime.indexOf("/");
        String finalDateTime = dateTime.substring(indexOfDateTime + 3).trim();
//        String[] unFormattedDateTime = txtArray[3].trim().split(" ");
//        String[] formattedDateTime = formatDateTime(unFormattedDateTime);
//        String finalDateTime = formattedDateTime[0] + " " + formattedDateTime[1];
        Deadline deadline = new Deadline(description, finalDateTime);
        if (isDoneInteger.equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }


    @Override
    public String toString() {
        return getType() + "|" + super.isDoneInteger() + "|"
                + description + "|" + by + System.lineSeparator();
    }
}


