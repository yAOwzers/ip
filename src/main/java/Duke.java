import command.Command;
import exceptions.DukeException;
import exceptions.DukeInvalidUserInputException;
import parser.Parser;
import storage.Storage;
import task.*;
import ui.Ui;
import response.ResultOfResponse;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Over seeing class of the Duke App.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a model of Duke with a specified file path.
     *
     * @param filePath file path.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList(new ArrayList<>());
        this.storage = new Storage(filePath);
        this.parser = new Parser(this.taskList, this.storage, this.ui);
    }

    public ResultOfResponse getResponse(String userInput) {
        try {
            Command userCommand = this.parser.parseCommand(userInput);
            return new ResultOfResponse(false, userCommand.execute());
        } catch (DukeException e) {
            return new ResultOfResponse(true, this.ui.showDukeError(e));
        }
    }

    /**
     * Initialises Duke.
     *
     * @return welcome message.
     */
    public String initDuke() {
        try {
            this.storage.loadTaskList(this.taskList);
        } catch (DukeInvalidUserInputException e) {
            return this.ui.showDukeError(e);
        }
        return Ui.greet();
    }

    public void run() {
        System.out.println(initDuke());
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            System.out.println(getResponse(userInput).getMessage());
        }
    }

        public static void main (String[]args) {
            Duke duke = new Duke("data/data.txt");
            duke.run();
        }

}
