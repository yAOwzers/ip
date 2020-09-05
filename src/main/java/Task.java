public class Task {
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
}


