package exceptions;

/**
 * Signals that a given command is not recognised by Duke.
 */
public class DukeIllegalCommandException extends DukeException {
    /**
     * @param command that is not recognised by Duke.
     */
    public DukeIllegalCommandException(String command) {
        super("Sorry but '" + command + "' is an unrecognizable command.");
    }
}
