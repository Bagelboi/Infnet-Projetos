package org.dlpk.Exercicio4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class LoginPageObject extends BasePageObject {
    // /login

    private final By signup_form = By.cssSelector(".signup-form form");
    private final By login_form = By.cssSelector(".login-form form");
    private final By email_input = By.name("email");
    private final By name_input = By.name("name");
    private final By password_input = By.name("password");
    private final By confirm_button = By.tagName("button");
    private final By error_msg = By.cssSelector("p[style='color: red;']");

    public LoginPageObject(WebDriver driver) throws IOException {
        super(driver);
        if (!driver.getCurrentUrl().contains("login"))
            throwErrorAndScreenshot("Pagina não é login", "erro_nao_login");
    }

    public String getErrorMessage() {
        //email ou senha incorreto
        //email ja registrado
        if (isPresent(error_msg))
            return q(error_msg).getText();
        return "";
    }

    public HomePageObject fillLoginForm(String email, String senha) throws IOException, InterruptedException {
        WebElement form = q(login_form);
        form.findElement(email_input).sendKeys(email);
        form.findElement(password_input).sendKeys(senha);
        form.findElement(confirm_button).click();
        wait(2000);
        return new HomePageObject(driver);
    }

    public RegisterPageObject fillSignupForm(String nome, String email) throws IOException, InterruptedException {
        WebElement form = q(signup_form);
        form.findElement(name_input).sendKeys(nome);
        form.findElement(email_input).sendKeys(email);
        form.findElement(confirm_button).click();
        wait(2000);
        return new RegisterPageObject(driver);
    }
}
