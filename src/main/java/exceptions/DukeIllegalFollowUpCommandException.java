package exceptions;

/**
 * Informs that a given follow up command is incorrect for a given command by Duke.
 */
public class DukeIllegalFollowUpCommandException extends DukeException {

    /**
     * @param command first command
     * @param followUpCommand second command
     */
    public DukeIllegalFollowUpCommandException(String command, String followUpCommand) {
        super("UHm this '" + followUpCommand + "' is an incorrect follow up command to a '"
                + command + "' command.");
    }
}
