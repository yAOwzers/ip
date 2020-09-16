package task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String printTask() {
        return "[" + getType() + "]" + super.printStatus() + " " + description + " (at: " + at + ")";
    }

    public static String getType(){
        return "E";
    }

    @Override
    public String toString() {
        return getType() + "|" + super.isDoneIntegar() + "|"
                + description + "|" + at + System.lineSeparator();
    }
}

