import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//App class is the hub for interacting with the user and printin stuff

//The manager for the rest of our code
public class App {

    private static Scanner userIn = new Scanner(System.in);

    //menu interface goes here
    public static void main(String[] args) {
        int currentMenuToDisplay = 0;
        int userSelection = -1;

        TaskList taskListManager = new TaskList();

        while (true) {
            displayMenu(currentMenuToDisplay);
            userSelection = getUserIn(currentMenuToDisplay);
            taskManager(userSelection, currentMenuToDisplay, taskListManager);
            currentMenuToDisplay = processUserInToUpdateMenu(userSelection, currentMenuToDisplay);
        }
    }

    private static void displayListOperationMenu() {
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

    private static void displayMainMenu() {
        System.out.printf("%nMAIN MENU%n---------%n");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.printf("3) Quit%n%n");
    }

    private static int getUserIn(int currentMenuToDisplay) {
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

    private static void checkValidMenuSelection(int userSelection, int currentMenuToDisplay) {
        if (currentMenuToDisplay == 0) {
            if (userSelection > 3 || userSelection < 1)
                throw new IllegalArgumentException("Menu Selection out of bounds! Must be an integer 1 - 3 for the main menu!%n");
        } else if (currentMenuToDisplay == 1) {
            if (userSelection > 8 || userSelection < 1)
                throw new IllegalArgumentException("Menu selection out of bounds! Must be an integer 1 - 8 for the task selection menu!%n");
        }
    }

    private static int processUserInToUpdateMenu(int userSelection, int currentMenuToDisplay) {
        if (currentMenuToDisplay == 0) {
            if (userSelection != 3)
                return 1;
            else
                System.exit(0);
        } else if (currentMenuToDisplay == 1) {
            if (userSelection != 8)
                return 1;
            else if (userSelection == 8)
                return 0;
        }
        return -1;
    }

    private static void displayMenu(int currentMenuToDisplay) {
        if (currentMenuToDisplay == 0)
            displayMainMenu();
        else if (currentMenuToDisplay == 1)
            displayListOperationMenu();
    }

    private static void taskManager(int usersInput, int currentMenu, TaskList currentTaskList) {
        if (currentMenu == 0) {
            mainMenuManager(usersInput, currentTaskList);
        } else if (currentMenu == 1) {
            taskListMenuManager(usersInput, currentTaskList);
        }
    }

    private static void mainMenuManager(int userInput, TaskList currentTask) {
        if (userInput == 1) {
            currentTask.createNewList();
        } else if (userInput == 2) {
            readFromFile(currentTask);
        }
    }

    private static void taskListMenuManager(int userInput, TaskList currentTaskList) {
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
            writeToFile(currentTaskList);
        }
    }

    private static TaskItem getUserInputToCreateTaskItem() {
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

    private static String getTitle() {
        String title;
        System.out.printf("Please enter a Title: ");
        title = userIn.nextLine();

        return title;
    }

    private static String getDescription() {
        System.out.printf("Please enter a description: ");
        return userIn.nextLine();
    }

    private static String getDueDate() {
        System.out.printf("Please enter a dueDate (yyyy-mm-dd): ");
        return userIn.nextLine();
    }

    private static int getIndexFromUser() {
        System.out.printf("Please select which task%n>");
        return userIn.nextInt();
    }

    private static void removeItemFromList(TaskList currentTaskList) {
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

    private static void markItemAsCompeleted(TaskList currentTaskList) {
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

    private static void markItemAsIncomplete(TaskList currentTaskList) {
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

    private static void editItemFromList(TaskList currentTask) {
        if (currentTask.getNumberOfTasks() == 0) {
            System.out.printf("Error, there are no tasks in the list to remove!%n");
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

    //All the stuff to write to files
    public static void writeToFile(TaskList myList) {
        userIn.nextLine();
        String filename = getFileName();
        if (doesFileExist(filename))
            if (doYouWantToOverRide())
                myList.write(filename, myList);
            else
                writeToFile(myList);
        else
            myList.write(filename, myList);
    }

    private static boolean doYouWantToOverRide() {
        String userResponse;
        System.out.println("This file already exists, do you want to override?" +
                " Information previously saved in the file will be lost! Enter y if you wish to continue, anything else to quit.");
        userResponse = userIn.next();
        return didTheyWantToContinue(userResponse);
    }

    private static boolean didTheyWantToContinue(String response) {
        if (response.toLowerCase().charAt(0) == 'y')
            return true;
        else
            return false;
    }

    private static String getFileName() {
        System.out.println("Enter the file name: ");
        return userIn.nextLine();
    }


    private static boolean doesFileExist(String filename) {
        File filecheck = new File(filename);
        return filecheck.exists();
    }

    private static File getFileNameAndCheck() {
        String filename = getFileName();
        if (!doesFileExist(filename)) {
            System.out.println("File not found!");
            getFileNameAndCheck();
        }
        File fileToRead = new File(filename);

        return fileToRead;
    }

    private static void readFromFile(TaskList myList) {
        userIn.nextLine();
        File fileToRead = getFileNameAndCheck();
        ArrayList<TaskItem> tempArray = getFileInputToCreateTaskItemListFromFile(fileToRead, myList);
        myList.loadExistingList(tempArray);

    }


    //all the stuff to read from files
    private static ArrayList<TaskItem> getFileInputToCreateTaskItemListFromFile(File fileToRead, TaskList myList) {
        ArrayList<TaskItem> tempList = new ArrayList<>();

        while (true) {
            try {
                Scanner fileScanner = new Scanner(fileToRead);
                while (fileScanner.hasNext())
                    tempList.add(new TaskItem(fileScanner.nextLine(), fileScanner.nextLine(), fileScanner.nextLine()));
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                readFromFile(myList);
            }
        }
        return tempList;
    }
}

