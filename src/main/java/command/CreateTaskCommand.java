package command;

import exceptions.DukeInvalidUserInputException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Abstract extension of the Command class for commands that
 * features creating tasks. Contains several methods that such
 * commands have in common.
 */
public abstract class CreateTaskCommand extends Command {

    protected TaskList taskList;
    protected Storage storage;
    protected String userInput;

    CreateTaskCommand(TaskList taskList, Storage storage, Ui ui, String userInput) {
        super(ui);
        this.taskList = taskList;
        this.storage = storage;
        this.userInput = userInput;
    }

    /**
     * Checks whether description is empty.
     * @param description to check.
     * @throws DukeInvalidUserInputException when there description is empty.
     */
    protected static void checkDescription(String description, String command) throws DukeInvalidUserInputException {
        if (description.isEmpty()) {
            throw new DukeInvalidUserInputException("The description of "
                    + command + " must not be empty.");
        }
    }

    /**
     * Checks whether there is a follow up command in the String array.
     * @param withoutCommandArray to check.
     * @throws DukeInvalidUserInputException when String array does not contain a follow up command.
     */
    protected static void checkFollowUpCommand(String[] withoutCommandArray, String followUpCommand)
            throws DukeInvalidUserInputException {
        if (withoutCommandArray.length < 2) {
            throw new DukeInvalidUserInputException("It appears you are missing a "
                    + "follow up '" + followUpCommand + "' command.");
        }
    }

    /**
     * Checks whether date time input exists.
     * @param dateTime to check.
     * @throws DukeInvalidUserInputException when date and time is missing.
     */
    protected static void checkDateTime (String dateTime, String command)
            throws DukeInvalidUserInputException {
        if (!dateTime.trim().contains(" ")) {
            throw new DukeInvalidUserInputException("The date and time is missing" +
                    " from your " + command + ".");
        }
    }

    protected String addTask(Task newTask) {
        this.taskList.add(newTask);
        this.storage.saveTask(newTask);
        return this.ui.printAddMessage(newTask) + "\n"
                + this.ui.getNumberOfTaskMessage(this.taskList.getTotalTask());
    }

}
