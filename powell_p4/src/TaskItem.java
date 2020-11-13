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
            throw new IllegalArgumentException("Error, date must be of the form yyyy-mm-dd using valid days and months," +
                    "check your response, and try again!");

        this.isComplete = false;
    }

    private boolean checkIsStringValid(String name){
        if(name.length() <= 0)
            return false;
        else return true;
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
            throw new IllegalArgumentException("Error, date must be of the form yyyy-mm-dd using valid days and months," +
                    "check your response, and try again!");
    }

    public void setTaskComplete(){
        this.isComplete = true;
    }

    public void setTaskIncomplete(){
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return ""+title+" "+description+" "+dueDate;
    }
}
