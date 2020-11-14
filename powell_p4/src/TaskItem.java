import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//Here's our object that stores the data
public class TaskItem {
    //has a title
    private String title;
    //one or more CHARACTERS in length
    //has a description
    private String description;
    //one or more chars
    //has a due date
    private String dueDate;
    //use the java date library to ensure YYYY-MM-DD valid format
    private boolean isComplete;


    public TaskItem(String title, String description, String dueDate){
        if(checkIsStringValid(title))
            this.title = title;
        else
            throw new IllegalArgumentException("Title must be atleast 1 char!");

        this.description = description;
        if(checkIsDateValid(dueDate))
            this.dueDate = dueDate;
        else
            throw new IllegalArgumentException("Error, date must be of the form yyyy-mm-dd using valid days and months");

        this.isComplete = false;
    }

    private boolean checkIsStringValid(String name){
        return name.length() > 0;
    }

    private boolean checkIsDateValid(String date){
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-M-d"));
        } catch (DateTimeParseException exc) {
            return false;
        }
        return true;
    }

    public void setTitle(String title){
        if(checkIsStringValid(title))
            this.title = title;
        else
            throw new IllegalArgumentException("Title must be atleast 1 character!");
    }

    public void setDueDate(String dueDate){
        if(checkIsDateValid(dueDate))
            this.dueDate = dueDate;
        else
            throw new IllegalArgumentException("Error, date must be of the form yyyy-mm-dd using valid days and months");
    }

    public void setTaskComplete(){
        this.isComplete = true;
    }

    public void setTaskIncomplete(){
        this.isComplete = false;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return ""+title+" "+description+" "+dueDate;
    }

    public void setDesription(String description) {
        this.description = description;
    }
}
