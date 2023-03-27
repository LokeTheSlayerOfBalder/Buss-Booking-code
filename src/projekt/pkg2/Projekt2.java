package projekt.pkg2;

import java.util.Arrays;
import java.util.Scanner;

public class Projekt2 {

    static Scanner scanner = new Scanner(System.in);

    public static int scanInt() {
        int number = 0;
        boolean success = false;
        while (!success) {

            try {
                number = scanner.nextInt();
                if (1 <= number && number <= 5) {
                    success = true;
                }
            } catch (Exception e) {
                System.out.println("Enter a valid number.");
                scanner.next();
            }

        }
        return number;
    }

    public static String scanName() {
        String name = " ";
        boolean success = false;
        while (!success) {

            try {
                name = scanner.nextLine();
                success = true;
            } catch (Exception e) {
                System.out.println("Invalid name. Please use only letters and spaces.");
                scanner.next();
            }

        }
        return name;
    }

    public static void main(String[] args) {

        String[] names = new String[21];
        int[] social = new int[21];
        int[] phone = new int[21];

        System.out.println("1. Add a passenger");
        System.out.println("2. Remove a passenger");
        System.out.println("3. Show passengers");
        System.out.println("4. Show earnings");
        System.out.println("5. Quit");

        int choice = scanInt();

        switch (choice) {
            case 1 -> {
                System.out.println("Passenger name:");
                scanName();
                System.out.println("Passenger socialsecurity number:");
                System.out.println("Passenger phonenumber:");
            }

        }
    }
}
