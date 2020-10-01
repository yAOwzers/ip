package command;

import exceptions.DukeIllegalFollowUpCommandException;
import exceptions.DukeInvalidUserInputException;
import parser.Parser;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;
/**
 * Adds an Event task into the task list of Duke.
 */
public class EventCommand extends CreateTaskCommand {
    /**
     * Constructs a event command.
     * @param taskList of Duke.
     * @param storage of Duke.
     * @param ui of Duke.
     * @param userInput details of tasks.
     */
    public EventCommand(TaskList taskList, Storage storage, Ui ui, String userInput) {
        super(taskList, storage, ui, userInput);
    }

    @Override
    public String execute() throws DukeInvalidUserInputException, DukeIllegalFollowUpCommandException {
        try {
            String userInputWithoutCommand = this.userInput.substring(this.userInput.indexOf(' '));
            String[] userInputWithoutCommandArray = userInputWithoutCommand.split("/");
            String description = userInputWithoutCommandArray[0].trim();
            checkDescription(description, "event");
            checkFollowUpCommand(userInputWithoutCommandArray, "/at");
            String followUpCommand = Parser.parseFollowUpCommand(userInputWithoutCommandArray[1]);
            if (followUpCommand.equals("at")) {
                checkDateTime(userInputWithoutCommandArray[1], "event");
                String dateAndTime = userInputWithoutCommandArray[1]
                        .substring(userInputWithoutCommandArray[1].indexOf(" ")).trim();
                Event newTask = new Event(description, dateAndTime);
                return addTask(newTask);
            } else {
                throw new DukeIllegalFollowUpCommandException("event", followUpCommand);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("The description of the event should not be empty!");
        }
    }

    @Override
    public String toString() {
        return "EventCommand";
    }
}
