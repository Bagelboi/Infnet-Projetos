package com.dlpk.CrudTP1;

import com.dlpk.CrudTP1.model.DTO.TransferenciaDTO;
import com.dlpk.CrudTP1.pagemodel.BasePageObject;
import com.dlpk.CrudTP1.pagemodel.SwaggerPageObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Disabled("Para testar workflows com mais agilidade")
public class UIErrorTest {
    private static WebDriver driver;
    private static String password = "macacoalbino123";
    private SwaggerPageObject swag_page;
    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
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
        //if (driver != null)
            //driver.quit();
    }

    @Test
    @Order(1)
    public void simTimeout() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(500));

        Assertions.assertThrows(
                org.openqa.selenium.TimeoutException.class,
                () -> gotoUrl(SwaggerPageObject.PATH)
        );
    }

    @Test
    @Order(2)
    public void overload() throws InterruptedException {
        gotoUrl(SwaggerPageObject.PATH);
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas", "POST");
        wait(500);
        swag_page.tryOutOpblock(opblock);
        swag_page.fillParamater(opblock, "senha_dada", password);
        swag_page.fillRequestBody(opblock, "Macaco Albino");
        for (int i = 0; i < 50; i++) {
            swag_page.executeOpBlock(opblock);
        }
    }

    @Test
    @Order(3)
    public void badEntry() throws InterruptedException, JsonProcessingException {
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas/transferir", "PUT");
        wait(500);
        swag_page.tryOutOpblock(opblock);
        swag_page.fillRequestBody(opblock, "habldsdjf");
        swag_page.executeOpBlock(opblock);
        Assertions.assertTrue( swag_page.responseBad(opblock) );

    }

    @Test
    @Order(4)
    public void sqlInjection() throws InterruptedException, JsonProcessingException {
        gotoUrl(SwaggerPageObject.PATH);
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas/transferir", "PUT");
        wait(500);
        swag_page.tryOutOpblock(opblock);
        String injectionPayload = "' OR '1'='1'";
        swag_page.fillRequestBody(opblock, injectionPayload);
        swag_page.executeOpBlock(opblock);
        Assertions.assertTrue( swag_page.responseBad(opblock) );

    }






}
