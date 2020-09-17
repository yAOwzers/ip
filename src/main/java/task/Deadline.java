package task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String printTask() {
        return "[" + getType() + "]" + super.printStatus() + " " + description + " (by: " + by + ")";
    }

    public static String getType(){
        return "D";
    }


    @Override
    public String toString() {
        return getType() + "|" + super.isDoneInteger() + "|"
                + description + "|" + by + System.lineSeparator();
    }
}


