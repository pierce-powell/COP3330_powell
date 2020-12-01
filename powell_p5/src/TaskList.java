//This class does all of our manipulations to the data and stores it in the list

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class TaskList extends List{

    private ArrayList<TaskItem> currentTaskList;


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
    @Override
    public void saveList(String filename) {
        try(Formatter outputToFile = new Formatter(filename)){
            //output the identity to ensure its one of our folders when loaded
            outputToFile.format("task list%n");
            for(int i = 0; i < currentTaskList.size(); i++){
                outputToFile.format("%s%n", currentTaskList.get(i).getDueDate()); //new line is crucial for our load
                outputToFile.format("%s%n", currentTaskList.get(i).getTitle());
                outputToFile.format("%s%n", currentTaskList.get(i).getDescription());
                if(currentTaskList.get(i).getIsComplete())
                    outputToFile.format("true%n");
                else
                    outputToFile.format("false%n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void loadExistingList(String filename) {
        //back up the list just in case
        ArrayList<TaskItem> backupList = new ArrayList<>();
        backupList = currentTaskList;
        currentTaskList = new ArrayList<>();

        try(Scanner inputFromFile = new Scanner(Paths.get(filename))) {
            //make sure our file has our identifier key
            String ident = inputFromFile.nextLine();
            if(ident.equals("task list")){
                //its a match
                while(inputFromFile.hasNext()) {
                    String curDueDate = inputFromFile.nextLine();
                    String curTitle = inputFromFile.nextLine();
                    String curDesc = inputFromFile.nextLine();
                    String curIsComplete = inputFromFile.nextLine();

                    TaskItem newItem = new TaskItem(curTitle, curDesc, curDueDate);

                    if(curIsComplete.equals("true"))
                        newItem.setTaskComplete();
                    else
                        newItem.setTaskIncomplete();

                    addItemToList(newItem);
                }
            }
            else{
                //this isn't one of our previously saved files
                currentTaskList = backupList;
                throw new IllegalArgumentException("Error! The filename provided has either been modified outside of the application" +
                        " or was not saved using the application! List restored to what it was before load!");
            }
        } catch (FileNotFoundException exc) {
            //revert the list
            currentTaskList = backupList;
            throw new IllegalArgumentException("Error loading the tasks! The filename you entered was not found, reverted to old list!");

        }catch (NoSuchFileException exc){
            currentTaskList = backupList;
            throw new IllegalArgumentException("Error loading the tasks! The filename you entered was not found, reverted to old list!");
        }
        catch (IOException e) {
            //unknown exception, so let it crash
            e.printStackTrace();
        }
        //check if the file name is a valid file that exists


    }
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
