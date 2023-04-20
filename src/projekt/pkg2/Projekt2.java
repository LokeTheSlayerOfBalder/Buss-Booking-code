package projekt.pkg2;

import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;

public class Projekt2 {

    static Scanner scanner = new Scanner(System.in);

    // int[] birthDates är den som vi kommer att använda som index i alla bokningar (see addPassangers metoden)
    public static LocalDate[] birthDates = new LocalDate[21];
    public static String[] firstNames = new String[21];
    public static String[] lastNames = new String[21];
    public static String[] genders = new String[21];
    public static int[] phone = new int[21];
    public static int oldBookings;
    public static int adultBookings;
    public static int childBookings;

    public static int scanInt(int interval1, int interval2) {
        //Denna funktion låter en endast skriva in en siffra i en interval. Annars levererar den ett fel medelande och man får försöka igen.
        int number = 0;
        boolean success = false;
        while (!success) {

            try {
                number = scanner.nextInt();
                if (number >= interval1 && number <= interval2) {
                    success = true;
                } else {
                    System.out.println("Enter a valid number");
                }
            } catch (Exception e) {
                System.out.println("Invalid number. Please enter a valid number.");
                scanner.next();
            }

        }
        return number;
    }

    public static String scanString() {
        //Denna funktion låter en endast skriva in en string. Annars levererar den ett fel medelande.
        String name = null;
        boolean success = false;
        while (!success) {

            try {
                name = scanner.nextLine();
                if (name != "") {
                    success = true;
                }
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
        LocalDate passangerBday = null;
        while (passangerBday == null) {
            System.out.println("Enter passenger birthdate:");
            System.out.println("Use YYYY-MM-DD formating");
            String dateString = scanString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            /*DateTimeFormatter och DateTimeFormatter.ofPattern låter programmet kontrollera att man använder rätt format av datum
            där yyyy menar år, MM menar datum med två siffror (så att januari blir 01 istället för 1) och dd menar dagar*/
            try {
                passangerBday = LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                //DateTimeParseExeption e är ett fel som kommer om man har använt fel format
                System.out.println("Invalid date! Please try again");
            }
        }
        LocalDate now = LocalDate.now();
        int age = Period.between(now, passangerBday).getYears();
        //LocalDate.now() och Period.between() är funktioner frånimport java.time.LocalDate; och import java.time.Period; som håller koll på tid och datum
        int seat = bookSeat(passangerBday);
        if (seat < birthDates.length) {
            System.out.println("You have successfully booked seat nr " + (seat + 1));
        } else {
            System.out.println("No available seats");
        }
        if (2023 - age >= 0 && 2023 - age < 18) {
            childBookings++;
        } else if (2023 - age >= 18 && 2023 - age < 69) {
            adultBookings++;
        } else {
            oldBookings++;
        }

        System.out.print("Enter passengers firstname: ");
        String firstName = scanString();
        firstNames[seat] = firstName;

        System.out.print("Enter passenger lastname: ");
        String lastName = scanString();
        lastNames[seat] = lastName;

        System.out.println("Passenger Gender");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");
        int genderChoice = scanInt(1, 3);
        switch (genderChoice) {
            case 1:
                genders[seat] = "Male";
                break;
            case 2:
                genders[seat] = "Female";
                break;
            case 3:
                genders[seat] = "Other";
                break;
            default:
                break;
        }

    }

    public static void showSeats() {
        for(int i = 0; i < birthDates.length; i++){
            if(birthDates[i] != null){
            System.out.println("Seat: " + (i + 1) + ", Name: " + firstNames[i] + " " + lastNames[i] + (", Birthdate: " + birthDates[i] + (", Gender: " + genders[i]) ));
            }
            else{
                System.out.println("Seat: " + (i + 1) + " is empty");
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("1. Add a passenger");
        System.out.println("2. Show free spaces");
        System.out.println("3. Show earnings");
        System.out.println("4. Quit");

       

        System.out.print("Choose one of the options above: ");
        int choice = scanInt(1, 4);

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
