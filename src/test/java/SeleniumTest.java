import io.github.bonigarcia.wdm.WebDriverManager;
import net.datafaker.Faker;
import org.dlpk.Exercicio4.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTest {

    private static WebDriver driver;
    private static Faker fakedata;
    private final String URL = "https://automationexercise.com";
    private static String email;
    private static String nome;
    private static String senha;
    private static Set<Cookie> cookies;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        fakedata = new Faker();
        email = fakedata.internet().emailAddress();
        nome = fakedata.name().firstName();
        senha = fakedata.babylon5().character();
    }

    private void wait(int milisec) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.of(milisec, ChronoUnit.MILLIS));
    }

    private void gotoUrl(String path) throws InterruptedException {
        driver.navigate().to(BasePageObject.BASE_URL + path);
        wait(1000);
    }

    @AfterAll
    public static void closeDriver() {
        if (driver != null)
            driver.quit();
    }


    @Test
    @Order(1)
    public void registrarUsuario() throws InterruptedException, IOException {
        gotoUrl("login");
        RegisterPageObject register = new LoginPageObject(driver).fillSignupForm(nome, email);
        AccountCreatedPageObject acc_created = register.fillRegisterForm(senha, "Daniel", "Gomes Lipkin",
                fakedata.company().name(), fakedata.address().streetAddress(), fakedata.address().secondaryAddress(),
                "Rio de Janeiro", "Rio de Janeiro", "12345678", "21421412",
                    "19", "May", "2003"
                );
    }

    @Test
    @Order(2)
    public void loginIncorreto() throws InterruptedException, IOException {
        gotoUrl("logout");
        gotoUrl("login");
        LoginPageObject login = new LoginPageObject(driver);
        try {
            login.fillLoginForm(email, "adsad");
        } catch (IllegalStateException e) {
            Assertions.assertEquals( login.getErrorMessage(), "Your email or password is incorrect!" );
        }
    }

    @Test
    @Order(3)
    public void loginCorreto() throws InterruptedException, IOException {
        gotoUrl("login");
        HomePageObject home = new LoginPageObject(driver).fillLoginForm(email, senha);
    }



}