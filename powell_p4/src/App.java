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

        while(true) {
            displayMenu(currentMenuToDisplay);
            userSelection = getUserIn(currentMenuToDisplay);
            TaskList.taskManager(userSelection, currentMenuToDisplay);
            currentMenuToDisplay = processUserInToUpdateMenu(userSelection, currentMenuToDisplay);
        }
    }


    private static void displayListOperationMenu() {
        System.out.printf("LIST OPERATION MENU%n-------------------%n");
        System.out.printf("1) View the list%n" +
                "2)Add an item%n" +
                "3)Edit an item%n" +
                "4)Remove an item%n" +
                "5)Mark an item as completed%n" +
                "6)Unmark an item as completed%n" +
                "7)Save the current list%n" +
                "8)Return to the main menu%n%n");
    }


    private static void displayMainMenu(){
        System.out.printf("MAIN MENU%n---------%n");
        System.out.println("1) Create a new list");
        System.out.println("2) Load an existing list");
        System.out.printf("3) Quit%n%n");
    }


    private static int getUserIn(int currentMenuToDisplay){
        int userSelection = 0;
        while(true) {
            try {
                userSelection = userIn.nextInt();
                checkValidMenuSelection(userSelection, currentMenuToDisplay);
                break;
            } catch (InputMismatchException exc){
                System.out.println("Please enter a valid integer!");
                userIn.nextLine();
            } catch (IllegalArgumentException exc){
                System.out.printf("" + exc.getMessage());
            }
        }
        return userSelection;
    }


    private static void checkValidMenuSelection(int userSelection, int currentMenuToDisplay){
        if(currentMenuToDisplay == 0){
            if(userSelection > 3 || userSelection < 1)
                throw new IllegalArgumentException("Menu Selection out of bounds! Must be an integer 1 - 3 for the main menu!%n");
        }
        else if (currentMenuToDisplay == 1){
            if(userSelection > 8 || userSelection < 1)
                throw new IllegalArgumentException("Menu selection out of bounds! Must be an integer 1 - 8 for the task selection menu!%n");
        }
    }


    private static int processUserInToUpdateMenu(int userSelection, int currentMenuToDisplay){
        if(currentMenuToDisplay == 0) {
            if (userSelection != 3)
                return 1;
            else
                System.exit(0);
        } else if (currentMenuToDisplay == 1){
            if(userSelection != 8 )
                return 1;
            else if(userSelection == 8)
                return 0;
        }
        return -1;
    }

    private static void displayMenu(int currentMenuToDisplay){
        if(currentMenuToDisplay == 0)
            displayMainMenu();
        else if (currentMenuToDisplay == 1)
            displayListOperationMenu();
    }



}
