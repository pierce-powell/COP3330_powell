//This class does all of our manipulations to the data and stores it in the list

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;

public class TaskList {

    private ArrayList<TaskItem> currentTaskList = new ArrayList<>();


    public void saveList(File filename) {


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

    public void loadExistingList() {
        System.out.println("Load Feature Not Implemented");
    }

    public void createNewList() { System.out.printf("New task list created!\n"); }

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

    public TaskItem getObjectFromSpecificIndex(int index){
        return currentTaskList.get(index);
    }

    public void write(String filename, TaskList myList) {
        try(Formatter output = new Formatter(filename)) {
            for(int i = 0; i < myList.getNumberOfTasks(); i++) {
                TaskItem data = myList.getObjectFromSpecificIndex(i);
                output.format("%s;%s;%s%n", data.getTitle(), data.getDescription(), data.getDueDate());
                System.out.println("file saved in "+ filename);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find the file... If the file exists, double check it ends in .txt");
            App.writeToFile(myList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
