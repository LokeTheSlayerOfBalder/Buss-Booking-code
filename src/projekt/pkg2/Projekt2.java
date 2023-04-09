package projekt.pkg2;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;

public class Projekt2 {

    static Scanner scanner = new Scanner(System.in);

    // int[] birthDates är den som vi kommer att använda i alla bokningar
    public static LocalDate[] birthDates = new LocalDate[21];
    public static String[] names = new String[21];
    public static int[] phone = new int[21];
    public static int oldBookings;
    public static int adultBookings;
    public static int childBookings;

    public static int scanInt() {
        //Denna funktion låter en endast skriva in en siffra annars levererar den ett fel medelande
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
        //Denna funktion låter en endast skriva in en string annars levererar den ett fel medelande
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

    public static int bookSeat(LocalDate date) {
        int seat = 0;
        boolean found = false;
        do {
            if (birthDates[seat] == null) {
                found = true;
                birthDates[seat] = date;
            } else {
                seat++;
            }
        } while (!found && seat < birthDates.length);
        return seat;
    }

    public static void addPassenger() {
        System.out.println("Enter passenger birthdate:");
        System.out.println("Use YYYY-MM-DD formating");
        String dateString = scanString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate passangerBday = LocalDate.parse(dateString, formatter);
        LocalDate now = LocalDate.now();
        int age = Period.between(now, passangerBday).getYears();
        int seat = bookSeat(passangerBday);
        if (seat < birthDates.length) {
            System.out.println("You have successfully booked seat nr " + (seat + 1));
        }else {
            System.out.println("No available seats");
        }
        if (2023 - age >= 0 && 2023 - age < 18) {
            childBookings++;
        } else if (2023 - age >= 18 && 2023 - age < 69) {
            adultBookings++;
        } else {
            oldBookings++;
        }

    }

    public static void showSeats() {

    }

    public static void main(String[] args) {

        System.out.println("1. Add a passenger");
        System.out.println("2. Show free spaces");
        System.out.println("3. Show earnings");
        System.out.println("4. Quit");

        int choice;

        do {
            System.out.print("Choose one of the options above: ");
            choice = scanInt();
        } while (choice > 5 || choice < 1);
        //Detta gör så att man endast kan välja ett av de möjliga alternativen

        switch (choice) {
            case 1 -> {
                addPassenger();
            }
            case 2 -> {
                showSeats();
            }

        }
    }
}
