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

    // tick: ✓ , Cross: ✘
    public String getStatusIcon() {
        return (isDone ? "*" : " "); //return tick or X symbols
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


    public static void decrementNumberOfTask() {
        numberOfTasks--;
    }

    public static String getType() {
        return "";
    }

    public int isDoneInteger() {
        if(isDone) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", isDone=" + isDone +
                '}';

    }
}


