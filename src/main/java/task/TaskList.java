package task;

import java.util.ArrayList;

/**
 * Represents an array list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task into the list and then generates and prints a success message.
     * @param task to be added into the list.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Adds a task into the list only.
     * Used when loading data from a storage file.
     * @param task to be added into the list.
     */
    public void load(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a specific task based on the task's index in the task array list
     * and then generates and prints a success message.
     * @param i index of task to be deleted.
     */
    public Task delete(int i) {
        Task task = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        return task;
    }

    /**
     * Marks a specific task as done based on task's index in the task array list
     * and then generates and prints a success message.
     * @param i index of task to be marked.
     */
    public Task markDone(int i) {
        Task task = this.taskList.get(i - 1);
        task.markAsDone();
        return task;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public int getTotalTask() {
        return this.taskList.size();
    }

}
