//This class does all of our manipulations to the data and stores it in the list

import java.util.ArrayList;

public class TaskList {

    private ArrayList<TaskItem> currentTaskList = new ArrayList<>();


    public static void taskManager(int usersInput, int currentMenu){
        if(currentMenu == 0){
            mainMenuManager(usersInput);
        }else if (currentMenu == 1){
            taskListMenuManager(usersInput);
        }
    }


    private static void mainMenuManager(int userInput){
        if(userInput == 1)
            createNewList();
        else if(userInput == 2)
            loadExistingList();
    }


    private static void taskListMenuManager(int userInput){
        if(userInput == 1)
            printList();
        else if(userInput == 2)
            addItemToList();
        else if(userInput == 3)
            editItemInList();
        else if(userInput == 4)
            removeItemFromList();
        else if(userInput == 5)
            markItemAsCompleted();
        else if(userInput == 6)
            unmarkItemAsCompleted();
        else if(userInput == 7)
            saveList();
    }

    private static void saveList() {
        System.out.printf("Saving list\n");
    }

    private static void unmarkItemAsCompleted() {
        System.out.printf("unmarking item\n");
    }

    private static void markItemAsCompleted() {
        System.out.printf("marking as complete\n");
    }

    private static void removeItemFromList() {
        System.out.printf("removing\n");
    }

    private static void editItemInList() {
        System.out.printf("editing\n");
    }

    private static void addItemToList() {
        System.out.printf("adding\n");
    }

    private static void printList() {
        System.out.printf("printing\n");
    }

    private static void loadExistingList() {
        System.out.printf("loading\n");
    }

    private static void createNewList() {
        System.out.printf("creating\n");
    }


    //has 0 or more items
    //user can create a new task list
    //save to the current task list
    //add an item
    //edit an item
    //mark an item as completed
    //unmark that same item

    //we will have our error handling for input here (assuming it's reading stuff into the obj)
}
