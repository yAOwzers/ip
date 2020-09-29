package task;

import exceptions.DukeInvalidUserInputException;

/**
 * A specific type of task that contains a description of a task and a specific date, start and end time.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String printTask() {
        return "[" + getType() + "]" + super.printStatus()
                + " " + description + " (at: " + at + ")";
    }

    /**
     * Converts the Event task into a string to be saved into a text file.
     * @return Event task in the form of a string.
     */
    public String toTextFormat() {
        return "E | " + super.toTextFormat() + " | " + description + " | " + at + System.lineSeparator();
    }

    public static String getType(){
        return "E";
    }

    /**
     * Parses a given string array into a Event task.
     * @param txtArray to be parsed into a Event task.
     * @return Event task based on input string array.
     * @throws DukeInvalidUserInputException when an invalid date and time format
     *     is found in the input string array.
     */
    public static Event parse(String[] txtArray) throws DukeInvalidUserInputException {


        String done = txtArray[1].trim();
        String description = txtArray[2].trim();
        String finalDateTime = "at";
//        String[] unFormattedDateTime = txtArray[3].trim().split(" ");
//        String[] formattedDateTime = formatDateTime(unFormattedDateTime);
//        String finalDateTime = formattedDateTime[0] + " " + formattedDateTime[1] + "-" + formattedDateTime[2];
        Event event = new Event(description, finalDateTime);
        if (done.equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return getType() + "|" + super.isDoneInteger() + "|"
                + description + "|" + at + System.lineSeparator();
    }
}

