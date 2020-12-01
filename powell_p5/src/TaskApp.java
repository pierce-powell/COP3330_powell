import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//App class is the hub for interacting with the user and printin stuff

//The manager for the rest of our code
public class TaskApp extends App {

    private static final Scanner userIn = new Scanner(System.in);
    private TaskList taskList;

    //wrappers that delegate the work
    public void runTaskApp() {
        int currentMenuToDisplay = 0;
        int userSelection = -1;


        TaskList taskListManager = new TaskList();

        while (currentMenuToDisplay != -1) {
            TaskApp curApp = new TaskApp();
            curApp.displayMenu(currentMenuToDisplay);
            userSelection = curApp.getUserIn(currentMenuToDisplay);
            curApp.taskManager(userSelection, currentMenuToDisplay, taskListManager);
            currentMenuToDisplay = curApp.processUserInToUpdateMenu(userSelection, currentMenuToDisplay);
        }
    }
    private void taskManager(int usersInput, int currentMenu, TaskList currentTaskList) {
        if (currentMenu == 0) {
            mainMenuManager(usersInput, currentTaskList);
        } else if (currentMenu == 1) {
            taskListMenuManager(usersInput, currentTaskList);
        }
    }
    private void mainMenuManager(int userInput, TaskList currentTask) {
        if (userInput == 1) {
            currentTask.createNewList();
        } else if (userInput == 2) {
            readFromFile(currentTask);
        }
        else if (userInput == 3)
            currentMenuToDisplay = -1;
    }



    private void taskListMenuManager(int userInput, TaskList currentTaskList) {
        if (userInput == 1) {
            currentTaskList.printList();
        } else if (userInput == 2) {
            //takes in user input send to task list to add and item
            currentTaskList.addItemToList(getUserInputToCreateTaskItem());
        } else if (userInput == 3) {
            //takes in user input, sends it to taskList?
            editItemFromList(currentTaskList);
        } else if (userInput == 4) {
            //Takes in the item to remove, sends it to task list
            removeItemFromList(currentTaskList);
        } else if (userInput == 5) {
            //Takes in the item to mark, send its to task list?
            markItemAsCompeleted(currentTaskList);
        } else if (userInput == 6) {
            //Takes in the item to unmark, send its to task list?
            markItemAsIncomplete(currentTaskList);
        } else if (userInput == 7) {
            saveToFile(currentTaskList);
        }
    }

    //stuff for menu display
    private void displayListOperationMenu() {
        System.out.printf("%nLIST OPERATION MENU%n-------------------%n");
        System.out.printf("1) View the list%n" +
                "2) Add an item%n" +
                "3) Edit an item%n" +
                "4) Remove an item%n" +
                "5) Mark an item as completed%n" +
                "6) Unmark an item as completed%n" +
                "7) Save the current list%n" +
                "8) Return to the main menu%n%n");
    }
    private void displayMainMenu() {
        System.out.printf("%nMAIN MENU%n---------%n");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.printf("3) Quit%n%n");
    }
    private void displayMenu(int currentMenuToDisplay) {
        if (currentMenuToDisplay == 0)
            displayMainMenu();
        else if (currentMenuToDisplay == 1)
            displayListOperationMenu();
    }

    //stuff for the user input and checking said input
    private int getUserIn(int currentMenuToDisplay) {
        int userSelection = 0;
        while (true) {
            try {
                userSelection = userIn.nextInt();
                checkValidMenuSelection(userSelection, currentMenuToDisplay);
                break;
            } catch (InputMismatchException exc) {
                System.out.println("Please enter a valid integer!");
                userIn.nextLine();
            } catch (IllegalArgumentException exc) {
                System.out.printf("" + exc.getMessage());
            }
        }
        return userSelection;
    }
    private void checkValidMenuSelection(int userSelection, int currentMenuToDisplay) {
        if (currentMenuToDisplay == 0) {
            if (userSelection > 3 || userSelection < 1)
                throw new IllegalArgumentException("Menu Selection out of bounds! Must be an integer 1 - 3 for the main menu!%n");
        } else if (currentMenuToDisplay == 1) {
            if (userSelection > 8 || userSelection < 1)
                throw new IllegalArgumentException("Menu selection out of bounds! Must be an integer 1 - 8 for the task selection menu!%n");
        }
    }
    private TaskItem getUserInputToCreateTaskItem() {
        TaskItem item = null;
        userIn.nextLine();
        while (true) {
            try {
                item = new TaskItem(getTitle(), getDescription(), getDueDate());
                break;
            } catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage() + " Task not created! Try again");
            }
        }
        return item;
    }
    private String getTitle() {
        String title;
        System.out.printf("Please enter a Title: ");
        title = userIn.nextLine();

        return title;
    }
    private String getDescription() {
        System.out.printf("Please enter a description: ");
        return userIn.nextLine();
    }
    private String getDueDate() {
        System.out.printf("Please enter a dueDate (yyyy-mm-dd): ");
        return userIn.nextLine();
    }
    private String getFileNameFromUser(){
        System.out.printf("Please enter the filename: ");
        return userIn.nextLine();
    }
    private int getIndexFromUser() {
        System.out.printf("Please select which task%n>");
        return userIn.nextInt();
    }

    //main worker wrapper functions for the stuff that needs direct user interaction
    private int processUserInToUpdateMenu(int userSelection, int currentMenuToDisplay) {
        if (currentMenuToDisplay == 0) {
            if (userSelection != 3)
                return 1;
            else if(userSelection == 3)
                return -1;
        } else if (currentMenuToDisplay == 1) {
            if (userSelection != 8)
                return 1;
            else if (userSelection == 8)
                return 0;
        }
        return -1;
    }
    private void removeItemFromList(TaskList currentTaskList) {
        if (currentTaskList.getNumberOfTasks() == 0) {
            System.out.printf("Error, there are no tasks in the list to remove!%n");
            return;
        }
        while (true) {
            try {
                currentTaskList.printList();
                currentTaskList.removeTheItem(getIndexFromUser());
                break;
            } catch (IndexOutOfBoundsException exc) {
                System.out.println(exc.getMessage() + "please try again!%n");
            } catch (InputMismatchException exc) {
                System.out.println("Index must be a valid integer! Please try again.");
                userIn.nextLine();
            }
        }
    }
    private void markItemAsCompeleted(TaskList currentTaskList) {
        if (currentTaskList.getNumberOfTasks() == 0) {
            System.out.printf("Error, there are no tasks in the list to remove!%n");
            return;
        }
        while (true) {
            try {
                currentTaskList.printListOfIncompletedTasks();
                currentTaskList.markItemAsCompleted(getIndexFromUser());
                break;
            } catch (IndexOutOfBoundsException exc) {
                System.out.println(exc.getMessage() + " Please try again!");
            } catch (InputMismatchException exc) {
                System.out.println("Index must be a valid integer! Please try again.");
                userIn.nextLine();
            }
        }
    }
    private void markItemAsIncomplete(TaskList currentTaskList) {
        if (currentTaskList.getNumberOfTasks() == 0) {
            System.out.printf("Error, there are no tasks in the list to remove!%n");
            return;
        }
        while (true) {
            try {
                currentTaskList.printListOfCompletedTasks();
                currentTaskList.unmarkItemAsCompleted(getIndexFromUser());
                break;

            } catch (IndexOutOfBoundsException exc) {
                System.out.println(exc.getMessage() + " Please try again!");
            } catch (InputMismatchException exc) {
                System.out.println("Index must be a valid integer! Please try again.");
                userIn.nextLine();
            }
        }
    }
    private void editItemFromList(TaskList currentTask) {
        if (currentTask.getNumberOfTasks() == 0) {
            System.out.printf("Error, there are no tasks in the list to edit!%n");
            return;
        }
        while (true) {
            try {
                currentTask.printList();
                int index = getIndexFromUser();
                currentTask.checkIfEditIndexIsValid(index);
                currentTask.editTheItem(index, getUserInputToCreateTaskItem());
                break;
            } catch (IndexOutOfBoundsException exc) {
                System.out.println(exc.getMessage() + " please try again!");
            } catch (InputMismatchException exc) {
                System.out.println("Index must be a valid integer! Please try again.");
                userIn.nextLine();
            } catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage() + " Please try again.");
            }
        }
    }
    private void readFromFile(TaskList currentTask) {
           while(true) {
               try {
                   userIn.nextLine();
                   //call the proper method and send it the filename to load
                   currentTask.loadExistingList(getFileNameFromUser());
                   break;
               } catch (IllegalArgumentException exc) {
                   System.out.println(exc.getMessage());
               }
           }
    }
    private void saveToFile(TaskList currentTask) {
        if (currentTask.getNumberOfTasks() == 0) {
            System.out.printf("Error, there are no tasks in the list to save!%n");
            return;
        }
        while (true){
            try{
                userIn.nextLine();
                //call the proper method and send it the filename to load
                String fileName = getFileNameFromUser();
                currentTask.saveList(fileName);
                break;
            } catch(IllegalArgumentException exc){
                System.out.println(exc.getMessage());
            }
        }
    }
}