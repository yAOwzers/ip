package task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String printTask() {
        return type() + super.printStatus() + " " + description + " (at: " + at + ")";
    }

    public String type(){
        return "[E]";
    }
//    @Override
//    public String toString() {
//        return "[E]" + super.toString() + " (at: " + at + ")";
//    }
}

