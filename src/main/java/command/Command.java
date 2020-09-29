package command;

import exceptions.DukeException;
import ui.Ui;

/**
 * Base class of a command in Duke
 */
public abstract class Command {

    protected Ui ui;

    public Command (Ui ui) {
        this.ui = ui;
    }

    /**
     * Executes the given command.
     * @throws DukeException when a certain constraint has not been met
     */
    public abstract String execute() throws DukeException;
}
