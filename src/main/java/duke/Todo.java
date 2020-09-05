package duke;

public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public String printTask() {
        return type() + super.printStatus() + " " + description;
    }

    public String type(){
        return "[T]";
    }
}
