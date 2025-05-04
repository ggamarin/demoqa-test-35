package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class FillFormTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillAllFieldsDemoqaFormTest() {
        registrationPage.openPage()
                .setFirstName("Grigoriy")
                .setLastName("Gamarin")
                .setEmail("grisha@gmail.com")
                .setGender("Male")
                .setUserNumber("1234567890")
                .setDateOfBirth("13", "April", "1996")
                .setSubjectsInput("Chemistry")
                .setHobbies("Reading")
                .setUploadPicture("test.jpg")
                .setCurrentAddress("Test Address")
                .setState("NCR")
                .setCity("Gurgaon")
                .setSubmit();

        registrationPage.checkResult("Student Name", "Grigoriy Gamarin")
                .checkResult("Student Email", "grisha@gmail.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567890")
                .checkResult("Date of Birth", "13 April,1996")
                .checkResult("Subjects", "Chemistry")
                .checkResult("Hobbies", "Reading")
                .checkResult("Picture", "test.jpg")
                .checkResult("Address", "Test Address")
                .checkResult("State and City", "NCR Gurgaon");
    }
}