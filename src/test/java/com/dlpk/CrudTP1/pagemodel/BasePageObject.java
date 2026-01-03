package com.dlpk.CrudTP1.pagemodel;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class BasePageObject {
    //https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/
    protected WebDriver driver;
    public static final String BASE_URL = "http://localhost:8081";

    public static class WrongPageException extends RuntimeException {
        public WrongPageException(String url, String intended) {
            super( "Pagina errada para o pagemodel!" + url + " != " + intended );
        }
    }


    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    protected void wait(int milisec) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.of(milisec, ChronoUnit.MILLIS));
    }

    protected void selectOption(WebElement element, String value) {
        new Select(element).selectByVisibleText(value);
    }

    protected boolean isPresent(By seletor) {
        return !driver.findElements(seletor).isEmpty();
    }

    protected WebElement q(By seletor) {
        return driver.findElement(seletor);
    }

    protected List<WebElement> Q(By seletor) {
        return driver.findElements(seletor);
    }
}
