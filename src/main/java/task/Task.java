package task;

import exceptions.DukeInvalidUserInputException;

/**
 * Base class of a task.
 */
public abstract class Task {
    protected String description;
    protected String[] descriptionArray;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.descriptionArray = description.split(" ");
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTask() {
        return description;
    }

    /**
     * Obtains status icon(either tick or cross symbol) based on the Task's isDone status.
     * @return status icon string.
     */
    public String getStatusIcon() {
        return (isDone ? "*" : " ");
    }

    public String printStatus() {
        String symbol = getStatusIcon();
        return "[" + symbol + "]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String printTask() {
        return printStatus() + description;
    }

    public static String getType() {
        return "";
    }

    public int isDoneInteger() {
        if(isDone) {
            return 1;
        }
        return 0;
    }

    /**
     * Parses a given string into a specific type of task.
     * @param txtFormat to be parsed into a task.
     * @return a specific task type based on the txtFormat.
     * @throws DukeInvalidUserInputException when txtFormat is of invalid format to be parsed into a task.
     */
    public static Task parse(String txtFormat) throws DukeInvalidUserInputException {
        char firstLetter = txtFormat.charAt(0);
        String[] txtArray = txtFormat.split("\\|");
        if (firstLetter == 'T') {
            return ToDo.parse(txtArray);
        } else if (firstLetter == 'D') {
            return Deadline.parse(txtArray);
        } else if (firstLetter == 'E') {
            return Event.parse(txtArray);
        } else {
            throw new DukeInvalidUserInputException("Invalid Task type!");
        }
    }

    public String toTextFormat() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Checks whether the description of a task contains a specific keyword.
     * @param keyword to be checked.
     * @return whether the keyword is within the description.
     */
    public boolean containsKeyword(String keyword) {
        for (String word : descriptionArray) {
            if (word.toLowerCase().equals(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return printTask();
    }

}


