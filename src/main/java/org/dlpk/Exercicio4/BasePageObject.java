package org.dlpk.Exercicio4;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class BasePageObject {
    //https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/
    protected WebDriver driver;
    public static final String BASE_URL = "https://automationexercise.com/";

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String _nome) throws IOException {
        //https://stackoverflow.com/questions/3422262/how-to-take-a-screenshot-with-selenium-webdriver
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Paths.get("src", "screenshot_") + _nome + ".jpg"));
    }

    protected void throwErrorAndScreenshot(String errormsg, String scrshot_name) throws IOException {
            if (!scrshot_name.isEmpty())
                takeScreenshot(scrshot_name);
            throw new IllegalStateException(errormsg);
    }

    protected void wait(int milisec) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.of(milisec, ChronoUnit.MILLIS));
    }

    protected void killAds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "document.querySelectorAll('iframe, ins, .adsbygoogle').forEach(e => e.remove());"
        );
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
}
