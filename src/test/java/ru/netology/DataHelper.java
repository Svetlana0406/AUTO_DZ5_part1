package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataHelper {
    private static Faker faker = new Faker(new Locale("ru"));

    private DataHelper() {
    }


    public static String getNewDate(int plusDays) {
        return LocalDate.now().plusDays(plusDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getNewName() {
        return faker.name().fullName();
    }

    public static String getNewPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String getNewCity() {
        return faker.address().city();
    }
}
