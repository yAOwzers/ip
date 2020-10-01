package exceptions;

import exceptions.DukeException;
import ui.Ui;

/**
 * Informs a given user input is invalid and does not fulfill the proper formats.
 */
public class DukeInvalidUserInputException extends DukeException {
    /**
     * @param message that contains information related to the improper formats.
     */
    public DukeInvalidUserInputException(String message) {
        super(message + Ui.lineSeparator());
    }
}
