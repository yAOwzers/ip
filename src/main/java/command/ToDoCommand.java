package command;

import exceptions.DukeInvalidUserInputException;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * Adds a To_Do task into the current task list of Duke.
 */
public class ToDoCommand extends CreateTaskCommand {
    /**
     * Constructs a to do command.
     * @param taskList of Duke.
     * @param storage of Duke.
     * @param ui of Duke.
     * @param userInput details of tasks.
     */
    public ToDoCommand(TaskList taskList, Storage storage, Ui ui, String userInput) {
        super(taskList, storage, ui, userInput);
    }

    @Override
    public String execute() throws DukeInvalidUserInputException {
        try {
            String description = userInput.substring(5).trim();
            checkDescription(description, "todo");
            ToDo newTask = new ToDo(description);
            return addTask(newTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the "
                    + "description of a todo must not be empty.");
        }
    }

    @Override
    public String toString() {
        return "ToDoCommand";
    }
}
