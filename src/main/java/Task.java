import java.util.ArrayList;

/**
 * Class for each individual task and a list of the tasks
 */
public class Task {
    protected String name;
    protected boolean done;
    protected static ArrayList<Task> list_of_tasks = new ArrayList<>();

    /**
     * public constructor to create a new task
     * @param name name of the task
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
        list_of_tasks.add(this);
    }

    /**
     * print log for adding a task
     */
    public void log_add_task() {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + this + "\n"
                + "Now you have " + list_of_tasks.size() + " task(s) in the list.\n"
                + "____________________________________________________________");
    }

    /**
     * change task's done state to be true
     */
    private void finish_task() {
        this.done = true;
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + this + "\n"
                + "____________________________________________________________");
    }

    /**
     * find the task in the list and mark it as done
     * @param order the number the task is labelled as
     */
    public static void find_finished_task(int order) {
        Task complete_task = list_of_tasks.get(order - 1);
        complete_task.finish_task();
    }

    /**
     * remove task from the list
     * @param order the number the task is labelled as
     */
    public static void remove_task(int order) {
        System.out.println("____________________________________________________________\n"
                + "Okies, I have removed this task: \n"
                + list_of_tasks.get(order - 1) + "\n"
                + "Now you have " + (list_of_tasks.size() - 1) + " task(s) in the list.\n"
                + "____________________________________________________________");
        list_of_tasks.remove(order - 1);
    }

    /**
     * print list of task
     */
    public static void print_list_of_tasks() {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:\n");
        for (int i = 0; i < list_of_tasks.size(); i++) {
            System.out.println((i + 1) + "." + list_of_tasks.get(i).toString()
//                    "[" + list_of_tasks.get(i).getStatus() + "] "
//                            + list_of_tasks.get(i).name + "\n"
            );
        }
        System.out.println("____________________________________________________________");
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
