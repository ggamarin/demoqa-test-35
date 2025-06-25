package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {
    @BeforeAll
    static void setupEnvironment() {
            Configuration.baseUrl = "https://demoqa.com";
            Configuration.pageLoadStrategy = "eager";

            String browser = System.getProperty("browser", "chrome");
            String browserVersion = System.getProperty("browserVersion", "128.0");
            String browserSize = System.getProperty("browserSize", "1920x1080");
        }

    @BeforeEach
    void addLog() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        String selenoidHost = System.getProperty("selenoidHost", "selenoid.autotests.cloud");
        String login = System.getProperty("login", "user1");
        String pass = System.getProperty("pass", "1234");
        Configuration.remote = String.format("https://%s:%s@%s/wd/hub", login, pass, selenoidHost);
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
