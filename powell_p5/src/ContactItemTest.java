import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactItemTest {

    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            ContactItem t = new ContactItem("", "", "", "");
        });
    }


    @Test
    public void creationSucceedsWithBlankEmail(){
        ContactItem t = new ContactItem("John", "Doe", "", "666-666-6666");
        StringBuilder myString = new StringBuilder();
        myString.append("Name: John Doe").append(System.getProperty("line.separator"));
        myString.append("Email: ").append(System.getProperty("line.separator"));
        myString.append("Phone: 666-666-6666");
        assertEquals(t.toString(), myString.toString());
    }

    @Test
    public void creationSucceedsWithBlankFirstName(){
        ContactItem t = new ContactItem("", "Doe", "", "666-666-6666");
        StringBuilder myString = new StringBuilder();
        myString.append("Name:  Doe").append(System.getProperty("line.separator"));
        myString.append("Email: ").append(System.getProperty("line.separator"));
        myString.append("Phone: 666-666-6666");
        assertEquals(t.toString(), myString.toString());
    }

    @Test
    public void creationSucceedsWithBlankLastName(){
        ContactItem t = new ContactItem("John", "", "", "666-666-6666");
        StringBuilder myString = new StringBuilder();
        myString.append("Name: John ").append(System.getProperty("line.separator"));
        myString.append("Email: ").append(System.getProperty("line.separator"));
        myString.append("Phone: 666-666-6666");
        assertEquals(t.toString(), myString.toString());
    }

    @Test
    public void creationSucceedsWithBlankPhone(){
        ContactItem t = new ContactItem("John", "Doe", "", "");
        StringBuilder myString = new StringBuilder();
        myString.append("Name: John Doe").append(System.getProperty("line.separator"));
        myString.append("Email: ").append(System.getProperty("line.separator"));
        myString.append("Phone: ");
        assertEquals(t.toString(), myString.toString());
    }

    @Test
    public void creationSucceedsWithNonBlankValues(){
        ContactItem t = new ContactItem("John", "Doe", "beep@boop.com", "420-420-6969");
        StringBuilder myString = new StringBuilder();
        myString.append("Name: John Doe").append(System.getProperty("line.separator"));
        myString.append("Email: beep@boop.com").append(System.getProperty("line.separator"));
        myString.append("Phone: 420-420-6969");
        t.isContactItemEmpty();
        assertEquals(t.toString(), myString.toString());
    }

    @Test
    public void editingFailsWithAllBlankValues(){
        assertThrows(IllegalArgumentException.class, () -> {
            ContactItem t = new ContactItem("", "boop", "", "");
            t.setlName("");
            t.isContactItemEmpty();
        });
    }

    @Test
    public void editingSucceedsWithBlankEmail(){
        ContactItem t = new ContactItem("John", "Doe", "beep@boop.com", "420-420-6969");
        t.setEmail("");
        StringBuilder myString = new StringBuilder();
        myString.append("Name: John Doe").append(System.getProperty("line.separator"));
        myString.append("Email: ").append(System.getProperty("line.separator"));
        myString.append("Phone: 420-420-6969");
        t.isContactItemEmpty();
        assertEquals(t.toString(), myString.toString());
    }

    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactItem t = new ContactItem("John", "Doe", "beep@boop.com", "420-420-6969");
        t.setfName("");
        StringBuilder myString = new StringBuilder();
        myString.append("Name:  Doe").append(System.getProperty("line.separator"));
        myString.append("Email: beep@boop.com").append(System.getProperty("line.separator"));
        myString.append("Phone: 420-420-6969");
        t.isContactItemEmpty();
        assertEquals(t.toString(), myString.toString());
    }

    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactItem t = new ContactItem("John", "Doe", "beep@boop.com", "420-420-6969");
        t.setlName("");
        StringBuilder myString = new StringBuilder();
        myString.append("Name: John ").append(System.getProperty("line.separator"));
        myString.append("Email: beep@boop.com").append(System.getProperty("line.separator"));
        myString.append("Phone: 420-420-6969");
        t.isContactItemEmpty();
        assertEquals(t.toString(), myString.toString());
    }

    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactItem t = new ContactItem("John", "Doe", "beep@boop.com", "420-420-6969");
        t.setPhoneNum("");
        StringBuilder myString = new StringBuilder();
        myString.append("Name: John Doe").append(System.getProperty("line.separator"));
        myString.append("Email: beep@boop.com").append(System.getProperty("line.separator"));
        myString.append("Phone: ");
        t.isContactItemEmpty();
        assertEquals(t.toString(), myString.toString());

    }

    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactItem t = new ContactItem("John", "Doe", "beep@boop.com", "420-420-6969");
        t.setPhoneNum("696-696-4204");
        StringBuilder myString = new StringBuilder();
        myString.append("Name: John Doe").append(System.getProperty("line.separator"));
        myString.append("Email: beep@boop.com").append(System.getProperty("line.separator"));
        myString.append("Phone: 696-696-4204");
        t.isContactItemEmpty();
        assertEquals(t.toString(), myString.toString());
    }

    @Test
    public void testToString(){
        ContactItem t = new ContactItem("John", "Doe", "beep@boop.com", "420-420-6969");
        StringBuilder myString = new StringBuilder();
        myString.append("Name: John Doe").append(System.getProperty("line.separator"));
        myString.append("Email: beep@boop.com").append(System.getProperty("line.separator"));
        myString.append("Phone: 420-420-6969");
        t.isContactItemEmpty();
        assertEquals(t.toString(), myString.toString());
    }
}
