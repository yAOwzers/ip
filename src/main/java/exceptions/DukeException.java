package exceptions;

import ui.Ui;

/**
 * Parent class of all Duke Exceptions.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message + Ui.lineSeparator()) ;
    }
}
