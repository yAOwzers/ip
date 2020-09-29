package command;

import exceptions.DukeException;
import exceptions.DukeInvalidUserInputException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Deletes a specific task in the current task list of Duke.
 */
public class DeleteCommand extends Command{

    private TaskList taskList;
    private Storage storage;
    private String userInput;

    /**
     * Constructs a delete command.
     * @param taskList of Duke.
     * @param storage of Duke.
     * @param ui of Duke.
     * @param userInput details of command.
     */
    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, String userInput) {
        super(ui);
        this.taskList = taskList;
        this.storage = storage;
        this.userInput = userInput;
    }

    public DeleteCommand(Ui ui) {
        super(ui);
    }

    @Override
    public String execute() throws DukeException {
        //Get number after done keyword
        String intSubstring = userInput.substring(7);
        try {
            int indexNumber = Integer.parseInt(intSubstring);
            Task deletedTask = this.taskList.delete(indexNumber);
            this.storage.saveTaskList(this.taskList); //Overwrites current data.txt file
            return this.ui.showDelete(deletedTask)
                    + this.ui.getNumberOfTaskMessage(this.taskList.getTotalTask());
        } catch (NumberFormatException ex) {
            throw new DukeInvalidUserInputException("Please enter a valid number!");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeInvalidUserInputException("Sorry this does not exist!");
        }
    }

    @Override
    public String toString() {
        return "DeleteCommand";
    }

}

