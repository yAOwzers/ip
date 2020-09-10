package task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String printTask() {
        return type() + super.printStatus() + " " + description + " (by: " + by + ")";
    }

    public String type(){
        return "[D]";
    }
//    @Override
//    public String toString() {
//        return "[D]" + super.toString() + " (by: " + by + ")";
//    }
}


