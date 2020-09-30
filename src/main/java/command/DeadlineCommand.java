package command;

import exceptions.DukeIllegalFollowUpCommandException;
import exceptions.DukeInvalidUserInputException;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

/**
 * Adds a Deadline task into the current task list of Duke.
 */
public class DeadlineCommand extends CreateTaskCommand {

    /**
     * Constructs a deadline command.
     * @param taskList of Duke.
     * @param storage of Duke.
     * @param ui of Duke.
     * @param userInput details of tasks.
     */
    public DeadlineCommand(TaskList taskList, Storage storage, Ui ui, String userInput) {
        super(taskList, storage, ui, userInput);
    }

    @Override
    public String execute() throws DukeInvalidUserInputException, DukeIllegalFollowUpCommandException {
        try {
            String userInputWithoutCommand = this.userInput.substring(this.userInput.indexOf(' '));
            String[] userInputWithoutCommandArr = userInputWithoutCommand.split("/");
            String description = userInputWithoutCommandArr[0].trim();
            checkDescription(description, "deadline");
            checkFollowUpCommand(userInputWithoutCommandArr, "/by");
            String followUpCommand = Parser.parseFollowUpCommand(userInputWithoutCommandArr[1]);
            if (followUpCommand.equals("by")) {
//                String dateTime = userInputWithoutCommand.substring(3);
                int indexOfDateTime = userInputWithoutCommand.indexOf("/");
                String dateTime = userInputWithoutCommand.substring(indexOfDateTime + 3).trim();
//                int indexOfDateTime = userInputWithoutCommand.indexOf("/");
//                String dateTime = userInputWithoutCommand.substring(indexOfDateTime + 2).trim();
//                checkDateTime(userInputWithoutCommandArr[1], "deadline");
//                String dateTime = userInputWithoutCommandArr[1]
//                        .substring(userInputWithoutCommandArr[1].indexOf(" ")).trim();
                Deadline newTask = new Deadline(description, dateTime);
                return addTask(newTask);
            } else {
                throw new DukeIllegalFollowUpCommandException("deadline", followUpCommand);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("Invalid date and time. "
                    + "The format should be.");
        }
    }

    @Override
    public String toString() {
        return "DeadlineCommand";
    }
}
