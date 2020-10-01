package task;

import exceptions.DukeInvalidUserInputException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A specific type of task that contains a description of a task and a specific date, start and end time.
 */
public class Event extends Task {
    private LocalDate date;
    private LocalTime time;
    private String at;

    public Event(String description, String at) throws DukeInvalidUserInputException {
        super(description);
        convertDateTime(at);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
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
        return "E | " + super.toTextFormat() + " | " + at;
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
        String isDoneInteger = txtArray[1].trim();
        String description = txtArray[2].trim();
        String[] unFormattedDateAndTime = txtArray[3].trim().split(" ");
        String[] formattedDateAndTime = formatDateAndTime(unFormattedDateAndTime);
        String finalDateAndTime = formattedDateAndTime[0] + " " + formattedDateAndTime[1];
        Event event = new Event(description, finalDateAndTime);
        if (isDoneInteger.equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Converts the given string into a LocalDate and LocalTime to be
     *     stored as the Event's date,start and end time.
     * @param dateAndTime to be converted into LocalDate and LocalTime.
     * @throws DukeInvalidUserInputException when an invalid a date time format is used as input.
     */
    private void convertDateTime(String dateAndTime) throws DukeInvalidUserInputException {
        try {
            String[] dateAndTimeArray = dateAndTime.split(" ");
            this.date = LocalDate.parse(dateAndTimeArray[0]);
            this.time =
                    LocalTime.parse(dateAndTimeArray[1].substring(0, 2) + ":" + dateAndTimeArray[1].substring(2, 4));
            this.at = this.date.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + " "
                    + this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidUserInputException("You have entered an invalid date and time! "
                    + "Please follow the format: YYYY-MM-DD hhmm.");
        }
    }

    private static String[] formatDateAndTime(String[] unformattedDateAndTime) {
        String[] formattedDateAndTime = new String[3];

        String unformattedDate =
                unformattedDateAndTime[0] + " " + unformattedDateAndTime[1] + " " + unformattedDateAndTime[2];
        String unformattedTime =
                unformattedDateAndTime[3] + " " + unformattedDateAndTime[4];

        String formattedDate =
                LocalDate.parse(unformattedDate, DateTimeFormatter.ofPattern("d MMMM yyyy")).toString();
        String time =
                LocalTime.parse(unformattedTime, DateTimeFormatter.ofPattern("hh:mm a")).toString();

        String formattedTime = time.substring(0, time.indexOf(':'))
                + time.substring(time.indexOf(':') + 1);

        formattedDateAndTime[0] = formattedDate;
        formattedDateAndTime[1] = formattedTime;
        return formattedDateAndTime;
    }

    @Override
    public String toString() {
        return getType() + "|" + super.isDoneInteger() + "|"
                + description + "|" + at + System.lineSeparator();
    }
}

