import java.util.ArrayList;

/**
 * Class for each individual task and a list of the tasks
 */
public class Task {
    protected String name;
    protected boolean done;
    protected TaskList taskList;

    /**
     * public constructor to create a new task
     * @param name name of the task
     */
    public Task(String name, boolean done, TaskList tasklist) {
        this.name = name;
        this.done = done;
        this.taskList = tasklist;
        tasklist.addTask(this);
    }


    /**
     * print log for adding a task
     */
    public void log_add_task() {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + this + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.\n"
                + "____________________________________________________________");
    }

    /**
     * change task's done state to be true
     */
    public void finish_task() {
        this.done = true;
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + this + "\n"
                + "____________________________________________________________");
    }



    /**
     * @return state of task
     */
    public String getStatus() {
        return (done ? "X" : " ");
    }


    public String log_record() {
        int state;
        if (this.done) {
            state = 1;
        } else {
            state = 0;
        }
        return "D , " + state + " , " + name;
    }

}
