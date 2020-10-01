package command;

import ui.Ui;

/**
 * Ends the running processes of Duke.
 */
public class ByeCommand extends Command {
    public ByeCommand(Ui ui) {
        super(ui);
    }

    @Override
    public String execute() {
        System.out.println(this.ui.printExit());
        System.exit(0);
        return this.ui.printExit();
    }

    @Override
    public String toString() {
        return "ByeCommand";
    }
}
