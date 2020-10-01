package command;

import exceptions.DukeInvalidUserInputException;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * Adds a To-Do task into the task list of Duke.
 */
public class ToDoCommand extends CreateTaskCommand {
    /**
     * Constructs a to-do command.
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
            throw new DukeInvalidUserInputException("The description" +
                    " of a todo is empty. Please fill it up!");
        }
    }

    @Override
    public String toString() {
        return "ToDoCommand";
    }
}
