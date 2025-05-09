package utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class RandomUtils {

    private static final Faker faker = new Faker(new Locale("en-GB"));
    public String getRandomFirstName(){
        return faker.name().firstName();
    };

    public  String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public  String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public  String getRandomMobile() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public  String getRandomBirthDay() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public String getRandomBirthMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public  String getRandomBirthYear() {
        return String.valueOf(faker.number().numberBetween(1970, 2006));
    }

    public  String getRandomSubject() {
        return faker.options().option(
                "Chemistry", "Maths", "Physics", "Arts", "English",
                "Biology", "History", "Economics", "Computer Science");
    }

    public String getRandomHobby() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public  String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public String getRandomState() {
        return faker.options().option(
                "NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public  String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> throw new IllegalArgumentException(
                    "Неизвестное значение state: " + state);
        };
    }
}
