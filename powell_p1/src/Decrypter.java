import java.util.Arrays;

public class Decrypter {
    private String numToDecrypt;

    //String to Int
    private static int[] stringToInteger(String numToDecrypt){
        int arrayToDecrypt[] = new int[4];

        for(int i = 0; i < 4; i++){
            arrayToDecrypt[i] = numToDecrypt.charAt(i) - '0';
        }
        return arrayToDecrypt;
    }

    //swap 2nd dig with 4th
    private static int[] swapScndDigWithFrth(int [] arrayToDecrypt){
        int[] temp = new int[4];

        for(int i = 0; i < 4; i++)
            temp[i] = arrayToDecrypt[i];

        arrayToDecrypt[1] = temp[3];
        arrayToDecrypt[3] = temp[1];

        return arrayToDecrypt;
    }

    //swap 1st dig with 3rd
    private static int[] swapFirstDigWithThrd(int [] arrayToDecrypt){
        int[] temp = new int[4];

        for(int i = 0; i < 4; i++)
            temp[i] = arrayToDecrypt[i];

        arrayToDecrypt[0] = temp[2];
        arrayToDecrypt[2] = temp[0];

        return arrayToDecrypt;
    }

    //add 10 subtract 7 to mimic the reverse mod
    private static int[] addTenSubSeven(int[] arrayToDecrypt) {
        for(int i = 0; i < 4; i++){
            arrayToDecrypt[i] += 10;
            arrayToDecrypt[i] -= 7;
        }
        return arrayToDecrypt;
    }

    //modulus
    private static int[] swapForRemainderModTen(int[] arrayToDecrypt){
        for(int i = 0; i < 4; i++) {
            int temp = arrayToDecrypt[i];
            temp = temp % 10;
            arrayToDecrypt[i] = temp;
        }

        return arrayToDecrypt;
    }

    //intArrayToString
    private static String intArrayToString(int[] arrayToEncrypt){
        String encryptedArray = Arrays.toString(arrayToEncrypt)
                .replace(",", "")
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "");

        return encryptedArray;
    }


    public static String decrypt(String arrayToDecrypt){
        int[] temp = new int[4];
        String finalAns = "";

        temp = stringToInteger(arrayToDecrypt);
        temp = addTenSubSeven(temp);
        temp = swapForRemainderModTen(temp);
        temp = swapFirstDigWithThrd(temp);
        temp = swapScndDigWithFrth(temp);
        finalAns = intArrayToString(temp);

        return finalAns;
    }

}
