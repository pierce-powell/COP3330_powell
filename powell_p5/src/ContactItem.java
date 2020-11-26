public class ContactItem {
    private String fName;
    private String lName;
    private String email;
    private String phoneNum;
    private Boolean doesContainItem;

    public ContactItem(String fName, String lName, String email, String phoneNum) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNum = phoneNum;
        isContactItemEmpty();
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void isContactItemEmpty(){
        if(phoneNum.length() > 0 || fName.length() > 0 || lName.length() > 0 || email.length() > 0)
            return;
        else
            throw new IllegalArgumentException("Error, contact not created as all input were left blank! Please try again and provide " +
                    "input for atleast one of the contact categories!");
    }

    @Override
    public String toString() {
        StringBuilder myString = new StringBuilder();
        myString.append("Name: " + fName + " " + lName).append(System.getProperty("line.separator"));
        myString.append("Email: " + email).append(System.getProperty("line.separator"));
        myString.append("Phone: " + phoneNum);
        return myString.toString();
    }
}

