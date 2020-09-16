package task;

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
        return getType() + super.printStatus() + " " + description;
    }

    public static String getType(){
        return "[T]";
    }

    @Override
    public String toString() {
        return getType() + "|" + super.printStatus()
                + "|" + description;
    }
}
