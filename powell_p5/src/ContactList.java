import java.io.*;
import java.util.ArrayList;

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
    public void loadExistingList() {
        System.out.println("I swear the list is loaded, just exit the program and take my word for it, please bro please, mom said I can't play the xbox if I get points off");
    }

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

}