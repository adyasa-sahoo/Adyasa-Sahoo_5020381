package weekone;

class Task {
    int taskId;
    String taskName;
    String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task(" + taskId + ", " + taskName + ", " + status + ")";
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class Exercise5 {
    private TaskNode head;

    public Exercise5() {
        head = null;
    }

    // Add a task to the linked list
    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Search for a task by taskId
    public Task searchTask(int taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.taskId == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null; // Task not found
    }

    // Traverse and print all tasks
    public void traverseTasks() {
        TaskNode current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Delete a task by taskId
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false; // List is empty
        }
        if (head.task.taskId == taskId) {
            head = head.next;
            return true;
        }
        TaskNode current = head;
        while (current.next != null && current.next.task.taskId != taskId) {
            current = current.next;
        }
        if (current.next == null) {
            return false; // Task not found
        }
        current.next = current.next.next;
        return true;
    }

    public static void main(String[] args) {
        Exercise5 tms = new Exercise5();

        tms.addTask(new Task(1, "Design", "Pending"));
        tms.addTask(new Task(2, "Development", "In Progress"));
        tms.addTask(new Task(3, "Testing", "Pending"));

        System.out.println("All Tasks:");
        tms.traverseTasks();

        System.out.println("\nSearch for Task with ID 2:");
        System.out.println(tms.searchTask(2));

        System.out.println("\nDelete Task with ID 1:");
        tms.deleteTask(1);
        tms.traverseTasks();
    }
}
