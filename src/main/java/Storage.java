import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Storage {

    protected String filepath;
    protected TaskList tasks;

    public Storage(String filepath, TaskList tasks) {
        this.filepath = filepath;
        this.tasks = tasks;
    }

    public void load() throws IOException {
        File f = new File(filepath);

        // check if data folder exists, if not, create it
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();

        // check if du.txt exists, if not, create it
        if (f.createNewFile()) {

        } else {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String task_name = s.nextLine();
                read_task(task_name);
            }
        }
    }


    public void read_task(String task_name) {
        String[] task = task_name.split(" , ", 4);
        boolean done = false;
        Task t = null;
        if (Integer.parseInt(task[1]) == 1) {
            done = true;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (Objects.equals(task[0], "T")) {
            t = new Todo(task[2], done, tasks);
        } else if (Objects.equals(task[0], "D")) {
            t = new Deadline(task[2], done, tasks, LocalDateTime.parse(task[3], formatter));
        } else if (Objects.equals(task[0], "E")) {
            t = new Event(task[2], done, tasks, LocalDateTime.parse(task[3], formatter));
        }
    }

    public void update_records(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        for (Task t : tasks.getList_of_tasks()) {
            fw.write(t.log_record() + "\n");
        }
        fw.close();
    }



}
