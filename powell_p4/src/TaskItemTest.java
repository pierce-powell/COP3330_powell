import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {

    @Test
    public void creatingTaskItemFailsWithInvalidDueDate(){
        assertThrows(IllegalArgumentException.class, () -> {
            TaskItem test = new TaskItem("Task 1", "beep boop", "2342-23-14");
        } );
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle(){
        assertThrows(IllegalArgumentException.class, () -> {
            TaskItem test = new TaskItem("", "beep boop", "2342-8-8");
        } );
    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        TaskItem test = new TaskItem("Task 1", "beep boop", "2012-12-8");
        assertEquals(test.toString(), "Task 1 beep boop 2012-12-8");
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle(){
        TaskItem test = new TaskItem("Task 1", "beep boop", "2342-8-8");
        assertEquals(test.toString(), "Task 1 beep boop 2342-8-8");
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate(){
        TaskItem test = new TaskItem("Tasker", "beep boop", "2012-7-8");
        assertThrows(IllegalArgumentException.class, () -> {
            test.setDueDate("99/89/23");
        } );
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate(){
        TaskItem test = new TaskItem("Task 1", "beep boop", "2020-12-31");
        test.setDueDate("1212-12-12");
        assertEquals(test.toString(), "Task 1 beep boop 1212-12-12");
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle(){
        TaskItem test = new TaskItem("Tasker", "beep boop", "2012-7-8");
        assertThrows(IllegalArgumentException.class, () -> {
            test.setTitle("");
        } );
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle(){
        TaskItem test = new TaskItem("Task 1", "beep boop", "2020-12-31");
        test.setTitle("Task 1 sub 0");
        assertEquals(test.toString(), "Task 1 sub 0 beep boop 2020-12-31");
    }

}