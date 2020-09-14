import java.awt.*;
import java.util.Arrays;

public class Encrypter {
    private String numToEncrypt;

    //String to Int
    private static int[] stringToInteger(String numToEncrypt){
        int arrayToEncrypt[] = new int[4];

        for(int i = 0; i < 4; i++){
            arrayToEncrypt[i] = numToEncrypt.charAt(i) - '0';
        }
        return arrayToEncrypt;
    }

    //Add 7 to each digit
    private static int[] addSevenToEveryDigit(int[] arrayToEncrypt){
        for(int i = 0; i < 4; i++)
            arrayToEncrypt[i] = arrayToEncrypt[i] + 7;

        return arrayToEncrypt;
    }

    //Get remainder after dividing by 10
    private static int[] swapForRemainderModTen(int[] arrayToEncrypt){
        for(int i = 0; i < 4; i++) {
            int temp = arrayToEncrypt[i];
            temp = temp % 10;
            arrayToEncrypt[i] = temp;
        }

        return arrayToEncrypt;
    }

    //swap first digit with the third
    private static int[] swapFirstDigWithThrd(int [] arrayToEncrypt){
        int[] temp = new int[4];

        for(int i = 0; i < 4; i++)
            temp[i] = arrayToEncrypt[i];

        arrayToEncrypt[0] = temp[2];
        arrayToEncrypt[2] = temp[0];

        return arrayToEncrypt;
    }

    //swap second digit with the fourth
    private static int[] swapScndDigWithFrth(int [] arrayToEncrypt){
        int[] temp = new int[4];

        for(int i = 0; i < 4; i++)
            temp[i] = arrayToEncrypt[i];

        arrayToEncrypt[1] = temp[3];
        arrayToEncrypt[3] = temp[1];

        return arrayToEncrypt;
    }

    //Int to string
    private static String intArrayToString(int[] arrayToEncrypt){
        String encryptedArray = Arrays.toString(arrayToEncrypt)
                .replace(",", "")
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "");

        return encryptedArray;
    }


    public static String encrypt(String arrayToEncrypt){
        int[] temp = new int[4];
        String finalAns = "";

        temp = stringToInteger(arrayToEncrypt);
        temp = addSevenToEveryDigit(temp);
        temp = swapForRemainderModTen(temp);
        temp = swapFirstDigWithThrd(temp);
        temp = swapScndDigWithFrth(temp);
        finalAns = intArrayToString(temp);

        return finalAns;
    }

}