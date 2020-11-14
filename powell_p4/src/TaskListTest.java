import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addingTaskItemsIncreasesSize(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
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
        p.addItemToList(t);

        assertThrows(IndexOutOfBoundsException.class, () -> { p.markItemAsCompleted(50);
        } );
    }

    @Test
    public void editingTaskItemChangesValues(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1111-11-11");
        p.editItemInList(0, b);
        assertEquals("b", p.getObjectFromSpecificIndex(0).getTitle());
    }

    @Test
    public void editingTaskItemDescriptionChangesValue(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1111-11-11");
        p.editItemInList(0, b);
        assertEquals("a", p.getObjectFromSpecificIndex(0).getDescription());
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1111-11-11");
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.editItemInList(1, b);;
        } );
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1121-12-12");
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.editItemInList(1, b);;
        } );
    }

    @Test
    public void editingTaskItemTitleChangesValue(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1121-12-12");
        p.editItemInList(0, b);
        assertEquals("b", p.getObjectFromSpecificIndex(0).getTitle());
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "b", "1111-11-11");
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.editItemInList(1, b);;
        } );
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        TaskItem b = new TaskItem("b", "a", "1121-12-12");
        p.editItemInList(0, b);
        assertEquals("1121-12-12", p.getObjectFromSpecificIndex(0).getDueDate());
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.getObjectFromSpecificIndex(1).getDescription();;
        } );
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        //assertDoesNotThrow is not recognized for some reason. But Performing this check means there
            //was no exceptions thrown
        assertEquals("b", p.getObjectFromSpecificIndex(0).getDescription());
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.getObjectFromSpecificIndex(1).getDueDate();;
        } );
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        assertThrows(IndexOutOfBoundsException.class, () -> {         p.getObjectFromSpecificIndex(1).getDueDate();;
        } );
    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() {
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        //assertDoesNotThrow is not recognized for some reason. But Performing this check means there
            //was no exceptions thrown
        assertEquals("1111-11-11", p.getObjectFromSpecificIndex(0).getDueDate());
        }

    @Test
    public void removingTaskItemsDecreasesSize(){
        TaskItem t = new TaskItem("a", "b", "1111-11-11");
        TaskList p = new TaskList();
        p.addItemToList(t);
        p.removeItemFromList(0);
        assertEquals(0, p.getNumberOfTasks());
    }
    /* gettingTaskItemTitleSucceedsWithValidIndex()
     removingTaskItemsFailsWithInvalidIndex()
     savedTaskListCanBeLoaded()
     uncompletingTaskItemChangesStatus()
     uncompletingTaskItemFailsWithInvalidIndex()
    newTaskListIsEmpty()*/
}

