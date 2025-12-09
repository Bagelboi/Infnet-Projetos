package org.dlpk.Exercicio4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class RegisterPageObject extends BasePageObject {

    // /signup

    private final By gender_male = By.id("id_gender1");
    private final By password_input = By.id("password");

    private final By day_select = By.id("days");
    private final By month_select = By.id("months");
    private final By year_select = By.id("years");

    private final By first_name_input = By.id("first_name");
    private final By last_name_input = By.id("last_name");
    private final By company_input = By.id("company");
    private final By address1_input = By.id("address1");
    private final By address2_input = By.id("address2");

    private final By state_input = By.id("state");
    private final By city_input = By.id("city");
    private final By zipcode_input = By.id("zipcode");
    private final By mobile_input = By.id("mobile_number");

    private final By create_account_button = By.cssSelector("button[data-qa='create-account']");

    public RegisterPageObject(WebDriver driver) throws IOException {
        super(driver);
        if (!driver.getCurrentUrl().contains("signup"))
            throwErrorAndScreenshot("Pagina não é cadastro", "erro_nao_cadastro");
    }

    public AccountCreatedPageObject fillRegisterForm(
            String senha,
            String firstName,
            String lastName,
            String company,
            String address1,
            String address2,
            String state,
            String city,
            String zipcode,
            String mobile,
            String day,
            String month,
            String year) throws IOException, InterruptedException {

        q(gender_male).click();
        q(password_input).sendKeys(senha);

        selectOption(q(day_select), day);
        selectOption(q(month_select), month);
        selectOption(q(year_select), year);

        q(first_name_input).sendKeys(firstName);
        q(last_name_input).sendKeys(lastName);
        q(company_input).sendKeys(company);
        q(address1_input).sendKeys(address1);
        q(address2_input).sendKeys(address2);

        q(state_input).sendKeys(state);
        q(city_input).sendKeys(city);
        q(zipcode_input).sendKeys(zipcode);
        q(mobile_input).sendKeys(mobile);

        q(create_account_button).click();
        wait(2000);
        return new AccountCreatedPageObject(driver);
    }
}

