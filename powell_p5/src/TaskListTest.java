import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addingTaskItemsIncreasesSize(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        assertEquals(1, p.getNumberOfTasks());
    }

    @Test
    public void completingTaskItemChangesStatus(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        t.setTaskComplete();
        assertEquals(true, t.getIsComplete());
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);

        assertThrows(IndexOutOfBoundsException.class, () -> { p.markItemAsCompleted(50);
        } );
    }

    @Test
    public void editingTaskItemChangesValues(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1111-11-11");
        p.editTheItem(0, b);
        assertEquals("b", p.getObjectFromSpecificIndex(0).getTitle());
    }

    @Test
    public void editingTaskItemDescriptionChangesValue(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1111-11-11");
        p.editTheItem(0, b);
        assertEquals("a", p.getObjectFromSpecificIndex(0).getDescription());
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1111-11-11");
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.editTheItem(1, b);;
        } );
    }

    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1111-11-11");
        p.editTheItem(0, b);
        assertEquals("a", p.getObjectFromSpecificIndex(0).getDescription());
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1121-12-12");
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.editTheItem(1, b);;
        } );
    }

    @Test
    public void editingTaskItemTitleChangesValue(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1121-12-12");
        p.editTheItem(0, b);
        assertEquals("b", p.getObjectFromSpecificIndex(0).getTitle());
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "b", "1111-11-11");
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.editTheItem(1, b);;
        } );
    }

    @Test
    public void editingItemTitleFailsWithEmptyString() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        assertThrows(IllegalArgumentException.class, () -> {            TaskItem b = new TaskItem("", "b", "1111-11-11");
        p.editTheItem(1, b);;
        } );
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1121-12-12");
        p.editTheItem(0, b);
        assertEquals("1121-12-12", p.getObjectFromSpecificIndex(0).getDueDate());
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.getObjectFromSpecificIndex(1).getDescription();;
        } );
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        //assertDoesNotThrow is not recognized for some reason. But Performing this check means there
        //was no exceptions thrown
        assertEquals("b", p.getObjectFromSpecificIndex(0).getDescription());
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.getObjectFromSpecificIndex(1).getDueDate();
        } );
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        //assertDoesNotThrow is not recognized for some reason. But Performing this check means there
        //was no exceptions thrown
        assertEquals("1111-11-11", p.getObjectFromSpecificIndex(0).getDueDate());
    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.getObjectFromSpecificIndex(1).getTitle();
        } );
    }

    @Test
    public void removingTaskItemsDecreasesSize(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        p.removeTheItem(0);
        assertEquals(0, p.getNumberOfTasks());
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        //assertDoesNotThrow is not recognized for some reason. But Performing this check means there
        //was no exceptions thrown
        assertEquals("a", p.getObjectFromSpecificIndex(0).getTitle());
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.removeTheItem(1);
        } );

    }

    @Test
    public void uncompletingTaskItemChangesStatus(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        t.setTaskComplete();
        t.setTaskIncomplete();
        assertEquals(false, t.getIsComplete());
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.createNewList();
        p.addItemToList(t);
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.getObjectFromSpecificIndex(1).setTaskIncomplete();
        } );

    }

    @Test
    public void newTaskListIsEmpty(){
        TaskList p = new TaskList();
        p.createNewList();
        assertEquals(0, p.getNumberOfTasks());
    }

    /* savedTaskListCanBeLoaded()*/



}

