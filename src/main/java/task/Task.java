package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public String printTask() {
        return printStatus() + description;
    }

    public String printStatus() {
        String symbol = getStatusIcon();
        return "[" + symbol + "]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static String getType() {
        return "";
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}


