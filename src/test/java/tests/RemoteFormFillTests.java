package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import pages.components.TableComponent;

import java.util.Map;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Tag("demoqa")
public class RemoteFormFillTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true, "enableVideo", true));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    RegistrationPage registrationPage = new RegistrationPage();
    TableComponent tableComponent = new TableComponent();

    @Test
    @DisplayName("Проверка успешного заполнения всех полей формы")
    void shouldFillAllFieldsDemoqaFormTest() {
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
    }

