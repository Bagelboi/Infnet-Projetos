package org.dlpk.Exercicio4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Objects;

public class HomePageObject extends BasePageObject {
    // URL base

    public HomePageObject(WebDriver driver) throws IOException {
        super(driver);
        if (!isPresent(By.cssSelector(".girl")))
            throwErrorAndScreenshot("Pagina não é home", "erro_nao_home");
    }


}
