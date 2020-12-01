import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactListTest {

    @Test
    public void addingItemsIncreasesSize(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        assertEquals(0, t.getNumberOfContacts());
        t.addItemToList(i);
        assertEquals(1, t.getNumberOfContacts());
    }
    @Test
    public void editingItemsFailsWithAllBlankValues(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        assertThrows(IllegalArgumentException.class, () -> {
            ContactItem b = new ContactItem("", "", "", "");
            t.editTheItem(0, b);
        } );
    }
    @Test
    public void editingItemsFailsWithInvalidIndex(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            ContactItem b = new ContactItem("jo", "", "", "");
            t.editTheItem(1, b);
        } );
    }
    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        ContactItem b = new ContactItem("", "bob", "joebob@email.com", "727-420-6969");
        t.editTheItem(0, b);
        assertEquals("", t.getObjectFromSpecificIndex(0).getfName());
    }
    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        ContactItem b = new ContactItem("joe", "", "joebob@email.com", "727-420-6969");
        t.editTheItem(0, b);
        assertEquals("", t.getObjectFromSpecificIndex(0).getlName());
    }
    @Test
    public void editingSucceedsWithBlankEmail(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        ContactItem b = new ContactItem("joe", "bob", "", "727-420-6969");
        t.editTheItem(0, b);
        assertEquals("", t.getObjectFromSpecificIndex(0).getEmail());
    }
    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        ContactItem b = new ContactItem("joe", "bob", "joebob@email.com", "");
        t.editTheItem(0, b);
        assertEquals("", t.getObjectFromSpecificIndex(0).getPhoneNum());
    }
    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        ContactItem b = new ContactItem("paula", "dean", "pd@email.com", "420-420-9696");
        t.editTheItem(0, b);
        assertEquals(b, t.getObjectFromSpecificIndex(0));
    }
    @Test
    public void newListIsEmpty(){
        ContactList t = new ContactList();
        t.createNewList();
        assertEquals(0, t.getNumberOfContacts());
    }
    @Test
    public void removingItemsDecreasesSize(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        assertEquals(1, t.getNumberOfContacts());
        t.removeTheItem(0);
        assertEquals(0, t.getNumberOfContacts());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            t.removeTheItem(1);
        } );
    }
    @Test
    public void savedContactListCanBeLoaded(){
        ContactList t = new ContactList();
        ContactItem i = new ContactItem("joe", "bob", "joebob@email.com", "727-420-6969");
        t.createNewList();
        t.addItemToList(i);
        t.saveList("savehere");
        ContactList loadList = new ContactList();
        loadList.loadExistingList("savehere");
        assertEquals(1, loadList.getNumberOfContacts());
    }
}