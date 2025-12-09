package org.dlpk.Exercicio4;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AccountCreatedPageObject extends BasePageObject {
    // /account_created
    public AccountCreatedPageObject(WebDriver driver) throws IOException {
        super(driver);
        if (!driver.getCurrentUrl().contains("account_created"))
            throwErrorAndScreenshot("Pagina não é conta criada", "erro_nao_accountcreated");
    }
}
