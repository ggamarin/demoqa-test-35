package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;
import pages.components.TableComponent;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Tag("demoqa")
public class RemoteFormFillTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TableComponent tableComponent = new TableComponent();

    @Test
    @DisplayName("Проверка успешного заполнения всех полей формы")
    void shouldFillAllFormFieldsTest() {
        step("Open form", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });

        step("Fill form", () -> {
            registrationPage.setFirstName("Grigoriy")
                    .setLastName("Gamarin")
                    .setEmail("grisha@gmail.com")
                    .setGender("Male")
                    .setUserNumber("1234567890")
                    .setDateOfBirth("13", "April", "1996")
                    .setSubjectsInput("Chemistry")
                    .setHobbies("Reading")
                    .setUploadPicture("test.jpg")
                    .scrollIntoView()
                    .setCurrentAddress("Test Address")
                    .setState("NCR")
                    .setCity("Gurgaon")
                    .setSubmit();
        });

        step("Verify results", () -> {
            tableComponent.checkResult("Student Name", "Grigoriy Gamarin")
                    .checkResult("Student Email", "grisha@gmail.com")
                    .checkResult("Gender", "Male")
                    .checkResult("Mobile", "1234567890")
                    .checkResult("Date of Birth", "13 April,1996")
                    .checkResult("Subjects", "Chemistry")
                    .checkResult("Hobbies", "Reading")
                    .checkResult("Picture", "test.jpg")
                    .checkResult("Address", "Test Address")
                    .checkResult("State and City", "NCR Gurgaon");
        });
    }

    @Test
    @DisplayName("Проверка успешного заполнения только обязательных полей формы")
    void shouldFillRequiredFieldsTest() {
        step("Open form", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });
        step("Fill form", () -> {
            registrationPage.setFirstName("Grigoriy")
                    .setLastName("Gamarin")
                    .setGender("Male")
                    .setUserNumber("1234567890")
                    .scrollIntoView()
                    .setSubmit();
        });

        step("Verify results", () -> {
            tableComponent.checkResult("Student Name", "Grigoriy Gamarin")
                    .checkResult("Gender", "Male")
                    .checkResult("Mobile", "1234567890");
        });
    }

    @Test
    @DisplayName("Валидация заполения обязательных полей формы")
    void shouldValidateFormFieldsTest() {
        step("Open form", () -> {
            registrationPage.openPage()
                    .removeBanner()
                    .scrollIntoView();
        });
        step("Submit form", () -> {
            registrationPage.setSubmit();
        });
        step("Verify results", () -> {
            tableComponent.checkTableUnvisibility();
        });
    }
}

