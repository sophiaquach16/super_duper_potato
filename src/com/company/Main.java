package com.company;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws ParseException {
	    System.out.println("Welcome to the 100% Scientifically Accurate Birthday Calculator!");
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What's your birthday?");
        String birthday_string = keyboard.nextLine();

        System.out.println("That means you were born on a " + birth_weekday(birthday_string) + "!");
        String current_date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        String yearInString = String.valueOf(year); //get current year in string

        String this_year_birthday_string = birthday_string.substring(0, birthday_string.length() - 4) + yearInString; // change to this year
        System.out.println("This year it falls on a " + birth_weekday(this_year_birthday_string) + "...");
        System.out.println("And since today is " + current_date + ",");
        days_until_birthday(current_date, this_year_birthday_string, birthday_string);
    }

    public static String birth_weekday(String day) throws ParseException { // get day of week based on date string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // date format
        LocalDate dt = LocalDate.parse(day, formatter);
        return dt.getDayOfWeek().toString();
    }

    public static void days_until_birthday(String current_date, String this_year_birthday_string, String birthday_string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(current_date, formatter);
        LocalDate endDate = LocalDate.parse(this_year_birthday_string, formatter);

        System.out.print("there's only ");
        // edge case if birthday already passed
        if (Period.between(startDate, endDate).getDays() < 0) {
            System.out.print(Period.between(startDate, endDate).getDays() + 365);
        }
        else System.out.print(Period.between(startDate, endDate).getDays());

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int age = year - Integer.valueOf(birthday_string.substring(birthday_string.length() - 4));
        System.out.print(" more days until the next one when you turn " + age + "!");
    }
}
