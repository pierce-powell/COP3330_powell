import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//App class is the hub for interacting with the user and printin stuff

//The manager for the rest of our code
public class ContactApp extends App{

    private static final Scanner userIn = new Scanner(System.in);
    private ContactList contactList;

    //wrappers that delegate the work
    public void runContactApp() {
        int currentMenuToDisplay = 0;
        int userSelection = -1;


        ContactList contactListManager = new ContactList();

        while (currentMenuToDisplay != -1) {
            ContactApp curApp = new ContactApp();
            curApp.displayMenu(currentMenuToDisplay);
            userSelection = curApp.getUserIn(currentMenuToDisplay);
            curApp.contactManager(userSelection, currentMenuToDisplay, contactListManager);
            currentMenuToDisplay = curApp.processUserInToUpdateMenu(userSelection, currentMenuToDisplay);
        }
    }
    private void contactManager(int usersInput, int currentMenu, ContactList currentContactList) {
        if (currentMenu == 0) {
            mainMenuManager(usersInput, currentContactList);
        } else if (currentMenu == 1) {
            contactListMenuManager(usersInput, currentContactList);
        }
    }
    private void mainMenuManager(int userInput, ContactList currentContact) {
        if (userInput == 1) {
            currentContact.createNewList();
        } else if (userInput == 2) {
            readFromFile(currentContact);
        }
        else if (userInput == 3)
            currentMenuToDisplay = -1;
    }
    private void contactListMenuManager(int userInput, ContactList currentContactList) {
        if (userInput == 1) {
            currentContactList.printList();
        } else if (userInput == 2) {
            //takes in user input send to contact list to add and item
            currentContactList.addItemToList(getUserInputToCreateContactItem());
        } else if (userInput == 3) {
            //takes in user input, sends it to contactList?
            editItemFromList(currentContactList);
        } else if (userInput == 4) {
            //Takes in the item to remove, sends it to contact list
            removeItemFromList(currentContactList);
        } else if (userInput == 5) {
            System.out.println("DONT FORGET TO IMPLEMENT!!!!!!!!!!!!!!");
        }
    }

    //stuff for menu display
    private void displayListOperationMenu() {
        System.out.printf("%nLIST OPERATION MENU%n-------------------%n");
        System.out.printf("1) View the list%n" +
                "2) Add an item%n" +
                "3) Edit an item%n" +
                "4) Remove an item%n" +
                "5) Save the current list%n" +
                "6) Return to the main menu%n%n");
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
            if (userSelection > 6 || userSelection < 1)
                throw new IllegalArgumentException("Menu selection out of bounds! Must be an integer 1 - 6 for the contact selection menu!%n");
        }
    }
    private ContactItem getUserInputToCreateContactItem() {
        ContactItem item = null;
        userIn.nextLine();
        while (true) {
            try {
                item = new ContactItem(getFName(), getLName() ,getEmailAddr(), getPhoneNum());
                break;
            } catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage() + " Contact not created! Try again");
            }
        }
        return item;
    }
    private String getFName() {
        String FName;
        System.out.printf("Please enter a first name: ");
        FName = userIn.nextLine();

        return FName;
    }
    private String getLName() {
        String LName;
        System.out.printf("Please enter a last name: ");
        LName = userIn.nextLine();
        return LName;
    }
    private String getPhoneNum() {
        System.out.printf("Please enter a phone number: ");
        return userIn.nextLine();
    }
    private String getEmailAddr() {
        System.out.printf("Please enter a email address: ");
        return userIn.nextLine();
    }
    private int getIndexFromUser() {
        System.out.printf("Please select which contact%n>");
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
    private void removeItemFromList(ContactList currentContactList) {
        if (currentContactList.getNumberOfContacts() == 0) {
            System.out.printf("Error, there are no contacts in the list to remove!%n");
            return;
        }
        while (true) {
            try {
                currentContactList.printList();
                currentContactList.removeTheItem(getIndexFromUser());
                break;
            } catch (IndexOutOfBoundsException exc) {
                System.out.println(exc.getMessage() + "please try again!%n");
            } catch (InputMismatchException exc) {
                System.out.println("Index must be a valid integer! Please try again.");
                userIn.nextLine();
            }
        }
    }
    private void editItemFromList(ContactList currentContact) {
        if (currentContact.getNumberOfContacts() == 0) {
            System.out.printf("Error, there are no contacts in the list to remove!%n");
            return;
        }
        while (true) {
            try {
                currentContact.printList();
                int index = getIndexFromUser();
                currentContact.checkIfEditIndexIsValid(index);
                currentContact.editTheItem(index, getUserInputToCreateContactItem());
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
    private boolean doYouWantToOverRide() {
        String userResponse;
        System.out.println("This file already exists, do you want to override?" +
                " Information previously saved in the file will be lost! Enter y if you wish to continue, anything else to quit.");
        userResponse = userIn.next();
        return didTheyWantToContinue(userResponse);
    }
    private boolean didTheyWantToContinue(String response) {
        if (response.toLowerCase().charAt(0) == 'y')
            return true;
        else
            return false;
    }
    private static String getFileName() {
        System.out.println("Enter the file name: ");
        return userIn.nextLine();
    }
    private boolean doesFileExist(String filename) {
        File filecheck = new File(filename);
        return filecheck.exists();
    }
    private File getFileNameAndCheck() {
        String filename = getFileName();
        if (!doesFileExist(filename)) {
            System.out.println("File not found!");
            getFileNameAndCheck();
        }
        File fileToRead = new File(filename);

        return fileToRead;
    }
    private void readFromFile(ContactList myList) {
        userIn.nextLine();
        //File fileToRead = getFileNameAndCheck();
        myList.loadExistingList();

    }


}
