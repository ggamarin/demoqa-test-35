package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.TableComponent;
import static utils.RandomUtils.*;

public class FillFormTestWithFakerData extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TableComponent tableComponent = new TableComponent();
    TestData testData = new TestData();

    @Test
    @DisplayName("Проверка успешного заполнения всех полей формы")
    void shouldFillAllFieldsDemoqaFormTest() {
        registrationPage.openPage()
                .removeBanner()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setUserNumber(testData.mobile)
                .setDateOfBirth(testData.birthDay, testData.birthMonth, testData.birthYear)
                .setSubjectsInput(testData.subject)
                .setHobbies(testData.hobby)
                .setCurrentAddress(testData.address)
                .setUploadPicture("test.jpg")
                .setState(testData.state)
                .setCity(testData.city)
                .setSubmit();

        tableComponent.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Student Email", testData.email)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.mobile)
                .checkResult("Date of Birth", testData.birthDay + " " + testData.birthMonth + "," + testData.birthYear)
                .checkResult("Subjects", testData.subject)
                .checkResult("Hobbies", testData.hobby)
                .checkResult("Picture", "test.jpg")
                .checkResult("Address", testData.address)
                .checkResult("State and City", testData.state + " " + testData.city);
    }

    @Test
    @DisplayName("Проверка успешного заполнения только обязательных полей формы")
    void shouldFillRequiredFields() {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setGender(testData.gender)
                .setUserNumber(testData.mobile)
                .setSubmit();

        tableComponent.checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Gender", testData.gender)
                .checkResult("Mobile", testData.mobile);
    }

    @Test
    @DisplayName("Валидация заполения обязательных полей формы")
    void shouldValidateTestFormFields() {
        registrationPage.openPage()
                .setSubmit();
        tableComponent.checkTableUnvisibility();
    }
}