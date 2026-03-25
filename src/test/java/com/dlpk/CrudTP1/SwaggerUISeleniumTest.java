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
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Disabled("Para testar workflows com mais agilidade")
public class SwaggerUISeleniumTest {
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
    public void postReq() throws InterruptedException {
        gotoUrl(SwaggerPageObject.PATH);
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas", "POST");
        wait(500);
        swag_page.tryOutOpblock(opblock);
        swag_page.fillParamater(opblock, "senha_dada", password);

        for (int i = 0; i < 5; i++) {
            wait(500);
            swag_page.fillRequestBody(opblock, "Macaco Albino");
            swag_page.executeOpBlock(opblock);
            Assertions.assertTrue( swag_page.responseOk(opblock) );
        }


    }


    @Test
    @Order(2)
    public void deleteReq() throws InterruptedException {
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas/{id}", "DELETE");
        wait(500);
        swag_page.tryOutOpblock(opblock);
        swag_page.fillParamater(opblock, "senha_dada", password);
        swag_page.fillParamater(opblock, "id", "0");

        swag_page.executeOpBlock(opblock);
        Assertions.assertTrue( swag_page.responseOk(opblock) );
    }

    @Test
    @Order(3)
    public void transferirReq() throws InterruptedException, JsonProcessingException {
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas/transferir", "PUT");
        wait(500);
        swag_page.tryOutOpblock(opblock);
        swag_page.fillRequestBody(opblock, new TransferenciaDTO(1L, 50.0, 2L).toJsonString());
        swag_page.executeOpBlock(opblock);
        Assertions.assertTrue( swag_page.responseOk(opblock) );
    }

    @Test
    @Order(4)
    public void getReq() throws InterruptedException {
        swag_page = new SwaggerPageObject(driver);
        WebElement opblock = swag_page.getOpBlock("/contas", "GET");
        wait(500);
        swag_page.tryOutOpblock(opblock);
        swag_page.executeOpBlock(opblock);
        Assertions.assertTrue( swag_page.responseOk(opblock) );

    }



}
