package command;

import exceptions.DukeInvalidUserInputException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Marks a specific task in the current task list of model.Duke as done.
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
        //Get number after done keyword
        if (this.userInput.length() == 4) {
            throw new DukeInvalidUserInputException("Description must not be empty!");
        }
        try {
            String intSubstring = this.userInput.substring(5);
            int indexNumber = Integer.parseInt(intSubstring);
            Task taskDone = this.taskList.markDone(indexNumber);
            this.storage.saveTaskList(this.taskList); //Overwrites current data.txt file
            return this.ui.showMarkDone(taskDone);
        } catch (NumberFormatException ex) {
            throw new DukeInvalidUserInputException("Please enter a valid number.");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeInvalidUserInputException("Sorry this does not exist!");
        }
    }

    @Override
    public String toString() {
        return "DoneCommand";
    }
}
