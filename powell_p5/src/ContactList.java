import java.io.*;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ContactList extends List {

    private ArrayList<ContactItem> currentContactList;

    public void saveList(File filename) {


    }

    //wrappers that call some of the worker functions
    public void removeTheItem(int index){
        if(isIndexValid(index))
            removeItemFromList(index);
        else
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }
    public void editTheItem(int index, ContactItem newlyEditedContact){
        if(isIndexValid(index))
            editTheItemInList(index, newlyEditedContact);
        else
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }
    public void addItemToList(ContactItem currentContact) { currentContactList.add(currentContact); }


    //worker functions
    public void printList() {
        System.out.printf("Current List%n------------%n");
        for(int i = 0; i < getNumberOfContacts(); i++){
            printCurrentContact(i);
        }
    }
    public void createNewList() {
        currentContactList = new ArrayList<>();
        System.out.printf("New contact list created!\n"); }
    private void printCurrentContact(int index){
        System.out.printf("%d) ", index);
        System.out.printf("Name: %s %s%n" +
                        "Phone: %s%n" +
                        "Email: %s%n",
                currentContactList.get(index).getfName(),
                currentContactList.get(index).getlName(),
                currentContactList.get(index).getPhoneNum(),
                currentContactList.get(index).getEmail());
    }
    private void removeItemFromList(int index) { currentContactList.remove(index); }
    private void editTheItemInList(int index, ContactItem newlyEditedContact) { currentContactList.set(index, newlyEditedContact); }

    //helper functions
    public int getNumberOfContacts() {return currentContactList.size();}
    private boolean isIndexValid(int index){
        if(index < getNumberOfContacts() && index >= 0)
            return true;
        else
            return false;
    }
    public void checkIfEditIndexIsValid(int index){
        if(!isIndexValid(index))
            throw new IndexOutOfBoundsException("Error: index listed is out of bounds of the current list!");
    }


    public ContactItem getObjectFromSpecificIndex(int index){
        return currentContactList.get(index);
    }

    @Override
    public void saveList(String filename) {
        try(Formatter outputToFile = new Formatter(filename)){
            //output the identity to ensure its one of our folders when loaded
            outputToFile.format("contact list%n");
            for(int i = 0; i < currentContactList.size(); i++){
                outputToFile.format("%s%n", currentContactList.get(i).getfName()); //new line is crucial for our load
                outputToFile.format("%s%n", currentContactList.get(i).getlName());
                outputToFile.format("%s%n", currentContactList.get(i).getPhoneNum());
                outputToFile.format("%s%n", currentContactList.get(i).getEmail());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void loadExistingList(String filename) {
        //back up the list just in case
        ArrayList<ContactItem> backupList = new ArrayList<>();
        backupList = currentContactList;
        currentContactList = new ArrayList<>();

        try(Scanner inputFromFile = new Scanner(Paths.get(filename))) {
            //make sure our file has our identifier key
            String ident = inputFromFile.nextLine();
            if(ident.equals("contact list")){
                //its a match
                while(inputFromFile.hasNext()) {
                    String curFName = inputFromFile.nextLine();
                    String curLName = inputFromFile.nextLine();
                    String curPhoneNum = inputFromFile.nextLine();
                    String curEmail = inputFromFile.nextLine();

                    ContactItem newItem = new ContactItem(curFName, curLName, curEmail, curPhoneNum);

                    addItemToList(newItem);
                }
            }
            else{
                //this isn't one of our previously saved files
                currentContactList = backupList;
                throw new IllegalArgumentException("Error! The filename provided has either been modified outside of the application" +
                        " or was not saved using the application! List restored to what it was before load!");
            }
        } catch (FileNotFoundException exc) {
            //revert the list
            currentContactList = backupList;
            throw new IllegalArgumentException("Error loading the tasks! The filename you entered was not found, reverted to old list!");

        }catch (NoSuchFileException exc){
            currentContactList = backupList;
            throw new IllegalArgumentException("Error loading the tasks! The filename you entered was not found, reverted to old list!");
        }
        catch (IOException e) {
            //unknown exception, so let it crash
            e.printStackTrace();
        }
        //check if the file name is a valid file that exists
    }
}