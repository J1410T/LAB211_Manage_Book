package Service;

import java.util.Scanner;

public class InputService {
    //HANDLE INPUT EXCEPTION
    public static final Scanner SC = new Scanner(System.in);
    //ENTER INTEGER WITH MIN MAX BOUND
    public static int inputInt(String message, int min, int max) { //[0, Max value]
        //[0, 15], Enter value: 
        boolean isValid = false;
        int value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Integer.parseInt(SC.nextLine());
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Value is out of bound!");
                    System.out.println("");
                }
            } catch (Exception e) {
                System.out.println("Value is not valid!");
                System.out.println("");
            }
        }
        return value;
    }
    //ENTER INTEGER
    public static int inputInt(String message) {
        boolean isValid = false;
        int value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Integer.parseInt(SC.nextLine());
                isValid = true;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return value;
    }
    //ENTER DOUBLE WITH MIN MAX BOUND
    public static double inputDouble(String message, double min, double max) {
        boolean isValid = false;
        double value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Double.parseDouble(SC.nextLine());
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Value is out of bound!");
                    System.out.println("");
                }
            } catch (Exception e) {
                System.out.println("Value is not valid!");
                System.out.println("");
            }
        }
        return value;
    }
    //ENTER NON EMPTRY STRING
    public static String inputString(String message) {
        boolean isValid = false;
        String value = "";
        while (!isValid) {
            System.out.print(message);
            value = SC.nextLine();
            if (!value.isEmpty()) {
                isValid = true;
            } else {
                System.out.println("Value can not be empty!");
                System.out.println("");
            }
        }
        return value;
    }
    
    public static String inputPattern(String message, String pattern, String patternOnScreen){
        boolean isValid = false;
        String value = "";
        while(!isValid){
            System.out.print(message);
            value = SC.nextLine();
            if (value.matches(pattern)){
                //pattern: form
                isValid = true;
            }
            else{
                System.out.println("Value must be in format " + patternOnScreen);
                System.out.println("");
            }
        }
        return value;
    }
}
