import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FillFormTest {
    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
    }
        @Test
        void fillAllFieldsDemoqaForm() {
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
            $("#firstName").setValue("Grigoriy");
            $("#lastName").setValue("Gamarin");
            $("#userEmail").setValue("grisha@gmail.com");
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue("1234567890");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("April");
            $(".react-datepicker__year-select").selectOption("1996");
            $(".react-datepicker__month").$(byText("13")).click();
            $("#subjectsInput").click();
            $("#subjectsInput").setValue("Chemistry").pressEnter();
            $("#hobbiesWrapper").$(byText("Reading")).click();
            $("#uploadPicture").uploadFromClasspath("test.jpg");
            $("#currentAddress").setValue("Test Adress");
            $("#state").click();
            $("#state").$(byText("NCR")).click();
            $("#city").click();
            $("#city").$(byText("Gurgaon")).click();
            $("#submit").click();

            $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Grigoriy Gamarin"));
            $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("grisha@gmail.com"));
            $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
            $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
            $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("13 April,1996"));
            $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Chemistry"));
            $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
            $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("test.jpg"));
            $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Test Adress"));
            $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Gurgaon"));
        }
    }