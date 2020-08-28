public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void printTask() {
        String symbol = getStatusIcon();
        System.out.println("[" + symbol + "]" + this.description);
    }

    public void markAsDone() {
        this.isDone = true;
    }
}


