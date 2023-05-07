package projekt.pkg2;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Projekt2 {

    static Scanner scanner = new Scanner(System.in);

    // int[] birthDates är den som vi kommer att använda som index i alla bokningar (see addPassangers metoden)
    //Alla arrays som används för att hålla koll på information finns här så att metoderna kan nå dem
    public static LocalDate[] birthDates = new LocalDate[21];
    public static String[] firstNames = new String[21];
    public static String[] lastNames = new String[21];
    public static String[] genders = new String[21];
    public static int[] phone = new int[21];
    public static int[] windows = new int[]{1, 0, 0, 4, 5, 0, 0, 8, 9, 0, 0, 12, 13, 0, 0, 16, 17, 0, 0, 0, 21};

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
                if (!"".equals(name)) {
                    success = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid text. Please use only letters and spaces.");
                scanner.next();
            }

        }
        return name;
    }

    public static LocalDate date() {
        LocalDate passengerBday = null;
        while (passengerBday == null) {
            System.out.println("Enter passenger birthdate:");
            System.out.println("Use YYYY-MM-DD formating");
            String dateString = scanString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            /*DateTimeFormatter och DateTimeFormatter.ofPattern låter programmet kontrollera att man använder rätt format av datum
            där yyyy menar år, MM menar datum med två siffror (så att januari blir 01 istället för 1) och dd menar dagar*/
            try {
                passengerBday = LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                //DateTimeParseExeption e är ett fel som kommer om man har använt fel format
                System.out.println("Invalid date! Please try again");
            }
        }
        return passengerBday;
    }

    public static int bookSeat(LocalDate date) {
        int seat = 0;
        boolean found = false;
        System.out.println("Donyou want a window seat?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = scanInt(1, 2);
        switch (choice) {
            case 1 -> {
                do {
                    if (birthDates[seat] == null && windows[seat] != 0) {
                        found = true;
                        birthDates[seat] = date;
                    } else {
                        seat++;
                    }
                } while (!found && seat < birthDates.length);

            }
            case 2 -> {
                do {
                    if (birthDates[seat] == null && windows[seat] == 0) {
                        found = true;
                        birthDates[seat] = date;
                    } else {
                        seat++;
                    }
                } while (!found && seat < birthDates.length);

            }
        }
        return seat;
    }

    public static void addPassenger() {
        LocalDate passangerBday = date();
        LocalDate now = LocalDate.now();
        int age = Period.between(now, passangerBday).getYears();
        //LocalDate.now() och Period.between() är funktioner från import java.time.LocalDate; och import java.time.Period; som håller koll på tid och datum
        int seat = bookSeat(passangerBday);
        if (seat < birthDates.length) {
            System.out.println("You have successfully booked seat nr " + (seat + 1));
        } else {
            System.out.println("No available seats");
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
            case 1 -> {
                genders[seat] = "Male";
            }
            case 2 -> {
                genders[seat] = "Female";
            }
            case 3 -> {
                genders[seat] = "Other";
            }
        }

    }

    public static void removePassenger() {

        System.out.println("search passenger:");
        System.out.println("1. Name");
        System.out.println("2. Birthdate");
        int choice = scanInt(1, 2);

        switch (choice) {
            case 1 -> {
                String passengerFirstName = scanString();
                String passengerLastName = scanString();
                for (int i = 0; i < birthDates.length; i++) {
                    if(birthDates[i] != null){
                    if (firstNames[i].equalsIgnoreCase(passengerFirstName) && lastNames[i].equalsIgnoreCase(passengerLastName)) {

                        System.out.println("Passenger " + firstNames[i] + " " + lastNames[i] + " has been removed");

                    } else if ((lastNames[i].equalsIgnoreCase(passengerLastName) || firstNames[i].equalsIgnoreCase(passengerFirstName))) {
                        System.out.println("Remove passenger " + firstNames[i] + " " + lastNames[i] + "?");
                        System.out.println("1. Remove");
                        System.out.println("2. Cancel");
                        choice = scanInt(1, 2);
                        if (choice == 1) {
                            System.out.println("Seat " + (i + 1) + ": " + firstNames[i] + " " + lastNames[i] + " has been removed");
                            birthDates[i] = null;
                            firstNames[i] = null;
                            lastNames[i] = null;
                            genders[i] = null;
                        }

                    }
                    }
                }
            }
            case 2 -> {

                LocalDate passengerBday = date();
                for (int i = 0; i < birthDates.length; i++) {
                    if (passengerBday.equals(birthDates[i])) {
                        System.out.println("Seat " + (i + 1) + ": " + firstNames[i] + " " + lastNames[i] + " has been removed");
                        birthDates[i] = null;
                        firstNames[i] = null;
                        lastNames[i] = null;
                        genders[i] = null;
                    }
                }
            }
        }
    }

    public static double sumPrice(LocalDate[] list, int index) {
        if (index == list.length) {
            return 0;
        }

        double price = 0;
        if (list[index] != null) {
            if (Period.between(LocalDate.now(), list[index]).getYears() > 69) {
                price = 200;
            } else if (Period.between(LocalDate.now(), list[index]).getYears() > 18) {
                price = 299.9;
            } else {
                price = 149.9;
            }
        }
        double result = price + sumPrice(list, ++index);
        return result;

    }

    public static void showSeats() {
        for (int i = 0; i < birthDates.length; i++) {
            if (birthDates[i] != null) {
                System.out.println("Seat: " + (i + 1) + ", Name: " + firstNames[i] + " " + lastNames[i] + (", Birthdate: " + birthDates[i] + (", Gender: " + genders[i])));
            } else {
                System.out.println("Seat: " + (i + 1) + " is empty");
            }
        }
    }

    public static void findPassengerName(String firstname, String lastname) {
        for (int i = 0; i < birthDates.length; i++) {
            if (birthDates[i] != null) {
                if (firstNames[i].equalsIgnoreCase(firstname) && lastNames[i].equalsIgnoreCase(lastname)) {
                    System.out.println("Seat: " + (i + 1) + ", " + firstNames[i] + " " + lastNames[i] + ", " + birthDates[i] + ", " + genders[i]);

                }
            }
            for (i = 0; i < birthDates.length; i++) {
                if (birthDates[i] != null) {
                    if ((firstNames[i].equalsIgnoreCase(firstname) && !lastNames[i].equalsIgnoreCase(lastname)) || (!firstNames[i].equalsIgnoreCase(firstname) && lastNames[i].equalsIgnoreCase(lastname))) {
                        System.out.println("Seat: " + (i + 1) + ", " + firstNames[i] + " " + lastNames[i] + ", " + birthDates[i] + ", " + genders[i]);
                    }
                }
            }
        }
    }

    public static void findPassengerNumber(LocalDate number) {
        for (int i = 0; i < birthDates.length; i++) {
            if (birthDates[i] != null) {
                if (birthDates[i].equals(number)) {
                    System.out.println("Seat: " + (i + 1) + " " + firstNames[i] + " " + lastNames[i] + ", " + birthDates[i] + ", " + genders[i]);
                }
            }
        }
    }

    public static int[] getSortedIndexes(LocalDate[] dates) {
        int[] indexes = new int[dates.length];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }

        for (int i = 0; i < indexes.length - 1; i++) {
            for (int j = i + 1; j < indexes.length; j++) {
                if (dates[indexes[i]] != null && dates[indexes[j]] != null && dates[indexes[i]].compareTo(dates[indexes[j]]) > 0) {
                    int temp = indexes[i];
                    indexes[i] = indexes[j];
                    indexes[j] = temp;
                }
            }
        }

        return indexes;
    }

    public static void main(String[] args) {

        boolean run = true;

        while (run) {
            System.out.println("1. Add a passenger");
            System.out.println("2. remove passenger");
            System.out.println("3. Show free spaces");
            System.out.println("4. Find passenger");
            System.out.println("5. Show earnings");
            System.out.println("6. Sort list by age");
            System.out.println("7. Quit");

            System.out.print("Choose one of the options above: ");
            int choice = scanInt(1, 7);

            switch (choice) {
                case 1 -> {
                    addPassenger();
                }
                case 2 -> {
                    removePassenger();
                }
                case 3 -> {
                    showSeats();
                }
                case 4 -> {
                    System.out.println("Search for:");
                    System.out.println("1. Passenger name");
                    System.out.println("2. Passenger birthdate");
                    int choice2 = scanInt(1, 2);

                    switch (choice2) {
                        case 1 -> {
                            System.out.print("Passengers firstname:");
                            String firstname = scanString();
                            System.out.print("Passenger lastname:");
                            String lastname = scanString();
                            findPassengerName(firstname, lastname);
                        }
                        case 2 -> {
                            System.out.print("Passanger birthdate:");
                            LocalDate date = date();
                            findPassengerNumber(date);
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Earnings = " + sumPrice(birthDates, 0) + "kr");

                }
                case 6 -> {
                    int[] indexes = getSortedIndexes(birthDates);
                    for (int i = 0; i < indexes.length; i++) {
                        if (birthDates[indexes[i]] != null) {
                            System.out.println(firstNames[indexes[i]] + " " + lastNames[indexes[i]] + " " + birthDates[indexes[i]] + " " + genders[indexes[i]]);
                        }
                    }

                }
                case 7 -> {
                    run = false;
                }
            }
        }
    }
}
