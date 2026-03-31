package com.dlpk.CrudTP1;

import com.dlpk.CrudTP1.model.DTO.TransferenciaDTO;
import com.dlpk.CrudTP1.pagemodel.BasePageObject;
import com.dlpk.CrudTP1.pagemodel.SwaggerPageObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwaggerUISeleniumTest {

    private static WebDriver driver;

    @Value("${app.senha}")
    private String password;

    private SwaggerPageObject swag_page;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.of(5, ChronoUnit.SECONDS));
    }

    private void sleep(int milisec) throws InterruptedException {
        Thread.sleep(milisec);
    }

    private void gotoUrl(String path) throws InterruptedException {
        driver.navigate().to(BasePageObject.BASE_URL + path);
        sleep(10000);
    }

    @AfterAll
    public static void closeDriver() {
        if (driver != null)
            driver.quit();
    }

    @Test
    @Order(1)
    public void postReq() throws InterruptedException {
        gotoUrl(SwaggerPageObject.PATH);
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas", "POST");
        sleep(5000);
        swag_page.tryOutOpblock(opblock);
        swag_page.fillParamater(opblock, "senha_dada", password);

        for (int i = 0; i < 5; i++) {
            sleep(1500);
            swag_page.fillRequestBody(opblock, "Macaco Albino");
            swag_page.executeOpBlock(opblock);
            Assertions.assertTrue(swag_page.responseOk(opblock));
        }
    }

    @Test
    @Order(2)
    public void deleteReq() throws InterruptedException {

        gotoUrl(SwaggerPageObject.PATH);
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas/{id}", "DELETE");
        sleep(5000);
        swag_page.tryOutOpblock(opblock);
        swag_page.fillParamater(opblock, "senha_dada", password);
        swag_page.fillParamater(opblock, "id", "1");
        swag_page.executeOpBlock(opblock);
        Assertions.assertTrue(swag_page.responseOk(opblock));
    }

    @Test
    @Order(3)
    public void transferirReq() throws InterruptedException, JsonProcessingException {

        gotoUrl(SwaggerPageObject.PATH);
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas/transferir", "PUT");
        sleep(5000);
        swag_page.tryOutOpblock(opblock);

        swag_page.fillRequestBody(opblock, new TransferenciaDTO(2L, 50.0, 3L).toJsonString());
        swag_page.executeOpBlock(opblock);
        Assertions.assertTrue(swag_page.responseOk(opblock));
    }

    @Test
    @Order(4)
    public void getReq() throws InterruptedException {
        gotoUrl(SwaggerPageObject.PATH);
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas", "GET");
        sleep(5000);
        swag_page.tryOutOpblock(opblock);
        swag_page.executeOpBlock(opblock);
        Assertions.assertTrue(swag_page.responseOk(opblock));
    }
}
