package command;

import exceptions.DukeInvalidUserInputException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Marks a specific task in the task list of Duke as done.
 */
public class DoneCommand extends Command {

    private TaskList taskList;
    private Storage storage;
    private String userInput;

    /**
     * Constructs a done command.
     * @param taskList of Duke.
     * @param storage of Duke.
     * @param ui of Duke.
     * @param userInput details of command.
     */
    public DoneCommand(TaskList taskList, Storage storage, String userInput, Ui ui) {
        super(ui);
        this.taskList = taskList;
        this.storage = storage;
        this.userInput = userInput;
    }

    @Override
    public String execute() throws DukeInvalidUserInputException {
        // retrieves the number after the 'done' keyword
        if (this.userInput.length() == 4) {
            throw new DukeInvalidUserInputException("Description must not be empty!");
        }
        try {
            String intSubstring = this.userInput.substring(5);
            int indexOfNumberAfterDone = Integer.parseInt(intSubstring);
            Task taskDone = this.taskList.markDone(indexOfNumberAfterDone);
            this.storage.saveTaskList(this.taskList); // saves the file by overwriting the data.txt file
            return this.ui.showMarkDone(taskDone);
        } catch (NumberFormatException e) {
            throw new DukeInvalidUserInputException("Please enter a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("Sorry this does not exist!");
        }
    }

    @Override
    public String toString() {
        return "DoneCommand";
    }
}
