import com.google.gson.Gson;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    ****************************************************************
                    Welcome to the currency converter.
                    
                    Choose a valid option:
                    
                    1) Dollar =>> Argentine Peso
                    2) Argentine Peso =>> Dollar
                    3) Dollar =>> Brazilian Real
                    4) Brazilian Real =>> Dollar
                    5) Dollar =>> Colombian Peso
                    6) Colombian Peso =>> Dollar
                    7) Exit
                    
                    ******************************************************************
                    """);
            int option = keyboard.nextInt();
            if (option == 7) {
                break;
            }
            System.out.println("The program was successfully closed");
        }

    }
}