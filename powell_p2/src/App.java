import java.awt.*;
import java.net.SocketOption;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }

//NOTE
//Call the Scanner's nextLine() method after reading in a numeric value to clear the input buffer.

    //moreInput() function prompts user for Y/N returns a bool
    private static boolean moreInput() {
        char response;
        System.out.println("Do you wish to enter another value? (Y/N)");
        response = in.nextLine().charAt(0);
        response = Character.toUpperCase(response);

        if (response == 'Y')
            return true;
        else if (response == 'N')
            return false;
        else {
            System.out.println("Invalid Input please enter (Y/N)");
            return moreInput();
        }

    }

    //Not positive of the location of these boi's im thinking app tho
    //The method `getUserHeight` prompts the user to enter their height in inches and only accepts positive values.
    private static double getUserHeight() {
        double heightAnswer = 0;
        System.out.print("Please enter height in inches: ");
        heightAnswer = in.nextDouble();
        in.nextLine();
        if (heightAnswer < 0) {
            System.out.println("INVALID INPUT!\n");
            return getUserHeight();
        }
        return heightAnswer;
    }

    //The method `getUserWeight` prompts the user to enter their weight in pounds and only accepts positive values.
    private static double getUserWeight() {
        double weightAnswer = 0;
        System.out.print("Please enter weight in pounds: ");
        weightAnswer = in.nextDouble();
        in.nextLine();
        if (weightAnswer < 0) {
            System.out.println("INVALID INPUT!\n");
            return getUserHeight();
        }
        return weightAnswer;
    }

    //The method `displayBmiInfo` prints out a user's BMi score and category using the data contained in a BodyMassIndex object.
    public static void displayBmiInfo(BodyMassIndex bmi) {
        System.out.println("The BMI score for this user is: " + bmi.getBMIScore() +
                ", meaning this user is: " + intCategToStringMsg(bmi));
    }

    public static String intCategToStringMsg(BodyMassIndex bmi){
        if (bmi.getBMICateg() == 1)
            return "Underweight";
        else if (bmi.getBMICateg() == 2)
            return "Normal weight";
        else if (bmi.getBMICateg() == 3)
            return "Overweight";
        else if (bmi.getBMICateg() == 4)
            return "Obesity";
        else
            return "error";
    }

    //The method `displayBmiStatistics` prints out the average BMI score of the data.
    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        double avg = 0;
        for (int i = 0; i < bmiData.size(); i++) {
            avg += bmiData.get(i).getBMIScore();
        }
        System.out.println("The average BMI score is "+ (avg / bmiData.size()));
    }
}