package projekt.pkg2;

import java.util.Arrays;
import java.util.Scanner;

public class Projekt2 {

    static Scanner scanner = new Scanner(System.in);

    public static String[] names = new String[21];
    public static int[] birthdate = new int[21];
    public static int[] phone = new int[21];
    public static int bookings;

    public static int scanInt() {
        int number = 0;
        boolean success = false;
        while (!success) {

            try {
                number = scanner.nextInt();
                success = true;
            } catch (Exception e) {
                System.out.println("Invalid number. Please enter a valid number.");
                scanner.next();
            }

        }
        return number;
    }

    public static String scanString() {
        String name = null;
        boolean success = false;
        while (!success) {

            try {
                name = scanner.nextLine();
                success = true;
            } catch (Exception e) {
                System.out.println("Invalid text. Please use only letters and spaces.");
                scanner.next();
            }

        }
        return name;
    }

    public static void addPassenger() {
        System.out.println("Enter passenger birthdate:");
        int date = scanInt();
        for (int i = 0; i < birthdate.length; i++) {
            if (birthdate[i] == 0) {
                System.out.println("Is seat nr " + (i + 1) + " satisfactory?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("3. Cancel");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    birthdate[i] = date;
                    System.out.println("You have successfully booked seat nr " + (i + 1));
                    bookings++;
                    break;
                } else if (choice == 3) {
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {

        System.out.println("1. Add a passenger");
        System.out.println("2. Show free spaces");
        System.out.println("3. Show earnings");
        System.out.println("4. Quit");
        
        int choice;
        
        do {System.out.print("Choose an option: ");
        choice = scanInt();
        } while (choice > 5 || choice < 1);

        switch (choice) {
            case 1 -> {
                addPassenger();
            }

        }
    }
}
