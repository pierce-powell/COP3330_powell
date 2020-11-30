import java.util.Scanner;

public class App {

    //0 for App selection, 1 for main, 2 for ops
    protected int currentMenuToDisplay = 0;
    //current App 0 for no app, 1 for task, 2 for contact
    private int currentApp= 0;
    protected static final Scanner userIn = new Scanner(System.in);

    //keeps track of the menu to show
    //keeps track of the App
    //Once that is decided, it will just run one of the other Apps
    public static void main(String[] args) {
        App myApp = new App();
        myApp.run();
    }

    private void run() {
        int menuSelection;
        while(true){
            displayApplicationMenu();
            menuSelection = getApplicationMenuSelection();
            processUserInFromApplicationMenu(menuSelection);
        }

    }

    private void processUserInFromApplicationMenu(int selection){
        if(selection == 1) {
            //run AppTask
            TaskApp newTaskApp = new TaskApp();
            newTaskApp.runTaskApp();
        }
        else if(selection == 2) {
            //run AppContact
            ContactApp newContactApp = new ContactApp();
            newContactApp.runContactApp();
        }
        else if(selection == 3)
            System.exit(1);
    }

    private int getApplicationMenuSelection() {
        int temp;
        System.out.printf("%n> ");
        temp = userIn.nextInt();
        if (currentMenuToDisplay == 0 || currentMenuToDisplay == 1)
            if (temp > 3 || temp < 1) {
                System.out.println("Invalid input, please select one of the options listed above!");
                temp = getApplicationMenuSelection();
            }
        return temp;
    }

    private final void displayApplicationMenu(){
        System.out.printf("%nApplication Menu%n" +
                "--------------%n" +
                "1) Task List%n" +
                "2) Contact List%n" +
                "3) Exit%n");
    }





}
