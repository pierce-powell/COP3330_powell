//This class does all of our manipulations to the data and stores it in the list

import java.util.ArrayList;

public class TaskList {

    private ArrayList<TaskItem> currentTaskList = new ArrayList<>();


    public void saveList() {
        System.out.printf("Saving list\n");
    }

    public void removeItemFromList(int index) { currentTaskList.remove(index); }

    public void editItemInList(int index, TaskItem newlyEditedTask) { currentTaskList.set(index, newlyEditedTask); }

    public void addItemToList(TaskItem currentTask) { currentTaskList.add(currentTask); }

    public void printList() {
        System.out.printf("Current List%n------------%n");
        for(int i = 0; i < getNumberOfTasks(); i++){
            printCurrentTask(i);
        }
    }

    public void loadExistingList() { System.out.printf("loading\n"); }

    public void createNewList() { System.out.printf("creating\n"); }

    public int getNumberOfTasks() {return currentTaskList.size();}

    private void printIndicatorIfCurrentTaskComplete(int index){
        if(isTheTaskCompleted(index))
            System.out.printf(" ***  ");
    }

    public void printCurrentTask(int index){
        System.out.printf("%d) ", index);
        printIndicatorIfCurrentTaskComplete(index);
        System.out.printf("[%s]  %s:  %s%n",
                currentTaskList.get(index).getDueDate(),
                currentTaskList.get(index).getTitle(),
                currentTaskList.get(index).getDescription());
    }

    private boolean isIndexValid(int index){
        if(index < getNumberOfTasks() && index >= 0)
            return true;
        else
            return false;
    }

    public void printListOfCompletedTasks(){
        System.out.printf("Compeleted Tasks%n------------%n");
        for(int i = 0; i < getNumberOfTasks(); i++){
            if(isTheTaskCompleted(i))
                printCurrentTask(i);
        }
    }

    public void printListOfIncompletedTasks(){
        System.out.printf("Incompeleted Tasks%n------------%n");
        for(int i = 0; i < getNumberOfTasks(); i++){
            if(!isTheTaskCompleted(i))
                printCurrentTask(i);
        }
    }

    private void setCurrentTaskToComplete(int index){ currentTaskList.get(index).setTaskComplete(); }

    public void unmarkItemAsCompleted(int index) {
        if(isIndexValid(index))
            setCurrentTaskToIncomplete(index);

        else
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");;
    }

    public void markItemAsCompleted(int index) {
        if(isIndexValid(index))
            setCurrentTaskToComplete(index);
        else
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }

    private void setCurrentTaskToIncomplete(int index){ currentTaskList.get(index).setTaskIncomplete(); }

    public void removeTheItem(int index){
        if(isIndexValid(index))
            removeItemFromList(index);
        else
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }

    public void editTheItem(int index, TaskItem newlyEditedTask){
        if(isIndexValid(index))
            editItemInList(index, newlyEditedTask);
        else
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }

    public void checkIfEditIndexIsValid(int index){
        if(!isIndexValid(index))
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }

    private boolean isTheTaskCompleted(int index){ return currentTaskList.get(index).getIsComplete(); }

    //This is a method ONLY used for JUNIT testing purposes
    public TaskItem getObjectFromSpecificIndex(int index){
        return currentTaskList.get(index);
    }
}
