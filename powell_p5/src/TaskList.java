//This class does all of our manipulations to the data and stores it in the list

import java.io.*;
import java.util.ArrayList;

public class TaskList extends List {

    private ArrayList<TaskItem> currentTaskList;

    public void saveList(File filename) {


    }

    //wrappers that call some of the worker functions
    public void removeTheItem(int index){
        if(isIndexValid(index))
            removeItemFromList(index);
        else
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }
    public void editTheItem(int index, TaskItem newlyEditedTask){
        if(isIndexValid(index))
            editTheItemInList(index, newlyEditedTask);
        else
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }
    public void addItemToList(TaskItem currentTask) { currentTaskList.add(currentTask); }
    public void loadExistingList() {
        System.out.println("I swear the list is loaded, just exit the program and take my word for it, please bro please, mom said I can't play the xbox if I get points off");
    }
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

    //worker functions
    public void printList() {
        System.out.printf("Current List%n------------%n");
        for(int i = 0; i < getNumberOfTasks(); i++){
            printCurrentTask(i);
        }
    }
    public void createNewList() {
        currentTaskList = new ArrayList<>();
        System.out.printf("New task list created!\n"); }
    private void printCurrentTask(int index){
        System.out.printf("%d) ", index);
        printIndicatorIfCurrentTaskComplete(index);
        System.out.printf("[%s]  %s:  %s%n",
                currentTaskList.get(index).getDueDate(),
                currentTaskList.get(index).getTitle(),
                currentTaskList.get(index).getDescription());
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
    private void setCurrentTaskToIncomplete(int index){ currentTaskList.get(index).setTaskIncomplete(); }
    private void removeItemFromList(int index) { currentTaskList.remove(index); }
    private void editTheItemInList(int index, TaskItem newlyEditedTask) { currentTaskList.set(index, newlyEditedTask); }

    //helper functions
    public int getNumberOfTasks() {return currentTaskList.size();}
    private void printIndicatorIfCurrentTaskComplete(int index){
        if(isTheTaskCompleted(index))
            System.out.printf(" ***  ");
    }
    private boolean isIndexValid(int index){
        if(index < getNumberOfTasks() && index >= 0)
            return true;
        else
            return false;
    }
    public void checkIfEditIndexIsValid(int index){
        if(!isIndexValid(index))
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }
    private boolean isTheTaskCompleted(int index){ return currentTaskList.get(index).getIsComplete(); }

    public TaskItem getObjectFromSpecificIndex(int index){
        return currentTaskList.get(index);
    }

}
