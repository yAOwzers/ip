package task;

import exceptions.DukeInvalidUserInputException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A specific type of task that contains a description of a task and a specific date and time.
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;
    private String by;

    /**
     * Constructs a deadline task.
     * @param description of task.
     * @param by of deadline task.
     * @throws DukeInvalidUserInputException when date time is incorrect format.
     */
    public Deadline(String description, String by) throws DukeInvalidUserInputException {
        super(description);
        convertDateTime(by);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
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
        return "D | " + super.toTextFormat() + " | " + this.by;
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
        String[] unformattedDateAndTime = txtArray[3].trim().split(" ");
        String[] formattedDateAndTime = formatDateAndTime(unformattedDateAndTime);
        String finalDateAndTime = formattedDateAndTime[0] + " " + formattedDateAndTime[1];
        Deadline deadline = new Deadline(description, finalDateAndTime);
        if (isDoneInteger.equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Converts the given string into a LocalDateTime to be stored in the given Deadline.
     * @param dateAndTime to be converted into LocalDateTime.
     * @throws DukeInvalidUserInputException when an invalid a date time format is used as input.
     */
    private void convertDateTime(String dateAndTime) throws DukeInvalidUserInputException {
        try {
            String[] dateAndTimeArray = dateAndTime.split(" ");
            this.date = LocalDate.parse(dateAndTimeArray[0]);
            this.time =
                    LocalTime.parse(dateAndTimeArray[1].substring(0, 2) + ":" + dateAndTimeArray[1].substring(2, 4));
            this.by = this.date.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + " "
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
                + description + "|" + by + System.lineSeparator();
    }
}


