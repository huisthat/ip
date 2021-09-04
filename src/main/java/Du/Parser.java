package Du;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Parser {
    private TaskList tasks;

    /**
     * Public constructor for Parser
     * @param tasks
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Takes in commands from user and parse them
     */
    public String parse(String command) {
        assert command != null : "Command cannot be nothing!";
        // remove any extra spaces if there is any
        command = command.strip();
        String response = "";
        if (Objects.equals(command, "bye")) {
            response = "Bye. Hope to not see you again:P";
        } else if (Objects.equals(command, "list")) {
            response = tasks.print_list_of_tasks();
        } else {
            response = parse_two_arguments(command);
        }
        assert response != "" : "there's no bot response?";
        return response;
    }

    /**
     * Takes in a command to parse as two arguments
     * @param command cammand taken in
     * @return String response
     */
    public String parse_two_arguments(String command) {
        String response = "";
        String[] split_string = command.split(" ", 2);
        if (Objects.equals(split_string[0], "done")) {
            if (split_string.length <= 1) {
                response = "Oh noes, you need to tell me what item you have finished";
                return response;
            }
            response = tasks.find_finished_task(Integer.parseInt(split_string[1])); // might want to find a way to check whether split_string[1] is an integer
        } else if (Objects.equals(split_string[0], "find")) {
            if (split_string.length <= 1) {
                response = "Oh noes, you need to tell me what you want to find";
                return response;
            }
            response = tasks.print(tasks.search(split_string[1]));
        } else if (Objects.equals(split_string[0], "delete")) {
            response = tasks.remove_task(Integer.parseInt(split_string[1])); // might want to find a way to check whether split_string[1] is an integer
        } else if (Objects.equals(split_string[0], "todo")) {
            // error handling when to do item is empty
            if (split_string.length <= 1) {
                response = "Oh noes, the todo item cannot be empty, please input again";
                return response;
            }
            Task task = new Todo(split_string[1], false, tasks);
            response = task.log_add_task();
        } else {
            if (Objects.equals(split_string[0], "deadline")) {
                response = parse_Deadline(split_string);
            } else if (Objects.equals(split_string[0], "event")) {
                response = parse_Event(split_string);
            } else {
                response = "Oh noes, I don't understand:(, please input again";
                return response;
            }
        }
        return response;
    }

    /**
     * Parse the deadline item
     * @param split_string command taken in
     * @return String response
     */
    public String parse_Deadline(String[] split_string) {
        String response = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            // error handling when the deadline item is empty
            if (split_string.length <= 1) {
                response = "Oh noes, the Deadline item cannot be empty, please input again";
                return response;
            }

            String[] task_time = split_string[1].split("/by ", 2);
            // error handling when there is no time for deadline
            if (task_time.length <= 1) {
                response = "Oh noes, the deadline item needs to have a time to be done by, please input again";
                return response;
            }

            try {
                LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
            } catch (DateTimeParseException e) {
                response = "Invalid date format, please input the date in this format: yyyy-MM-dd HH:mm";
                return response;
            }
            LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
            Task task = new Deadline(task_time[0], false, tasks, date);
            response = task.log_add_task();

        return response;
    }

    /**
     * Parse the event item
     * @param split_string command taken in
     * @return String response
     */
    public String parse_Event(String[] split_string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String response = "";
        // error handling when the event item is empty
        if (split_string.length <= 1) {
            response = "Oh noes, the event item cannot be empty, please input again";
            return response;
        }
        String[] task_time = split_string[1].split("/at ", 2);
        // error handling when there is no time for event
        if (task_time.length <= 1) {
            response = "Oh noes, the event item needs to have a time that it is occuring at, please input again";
            return response;
        }
        try {
            LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
        } catch (DateTimeParseException e) {
            response = "Invalid date format, please input the date in this format: yyyy-MM-dd HH:mm";
            return response;
        }
        LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
        Task task = new Event(task_time[0], false, tasks, date);
        response = task.log_add_task();
        return response;
    }


}
