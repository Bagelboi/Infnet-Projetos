package com.dlpk.CrudTP1.pagemodel;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class SwaggerPageObject extends BasePageObject {
    public static String PATH = "/swagger-ui/index.html";
    private final By opblock_selector = By.cssSelector(".opblock-summary-control");
    private final By opblock_execute = By.cssSelector(".execute");
    private final By opblock_tryout_execute = By.cssSelector(".try-out__btn");
    private final By opblock_params = By.cssSelector(".parameters-col_description input");
    private final By response_selector = By.cssSelector(".response .response-col_status");
    private final By opblock_request_body = By.cssSelector(".body-param textarea");

    public SwaggerPageObject(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains(PATH))
            throw new WrongPageException(driver.getCurrentUrl(), PATH);
    }

    public WebElement getOpBlock(String path, String type) {
        WebElement opblock = Q(opblock_selector).stream().filter(el -> {
            String[] stuff =  el.getText().split("\\n");
            return stuff[0].equals(type) && stuff[1].equals(path);
        }).findFirst().get();
        opblock.click();
        return opblock.findElement(By.xpath("../.."));
    }

    public void tryOutOpblock(WebElement opblock) {
        opblock.findElement(opblock_tryout_execute).click();
    }

    public void fillParamater(WebElement opblock, String paramname, String value) {
        opblock.findElements(opblock_params).stream()
                .filter(el -> el.getDomAttribute("placeholder").toString().equals(paramname))
                .findFirst().get().sendKeys(value);
    }

    public void fillRequestBody(WebElement opblock, String value) throws InterruptedException {
        WebElement textarea = opblock.findElement(opblock_request_body);
        textarea.click();
        textarea.sendKeys(Keys.CONTROL + "a");
        textarea.sendKeys(Keys.DELETE);
        textarea.sendKeys(value);
    }

    public void executeOpBlock(WebElement opblock) throws InterruptedException {
        try {
            opblock.findElement(opblock_execute).click();
        } catch (RuntimeException e) {
            tryOutOpblock(opblock);
            wait(200);
            opblock.findElement(opblock_execute).click();
        }
    }

    public boolean responseOk(WebElement opblock) {
       int res_code = Integer.parseInt( opblock.findElement(response_selector).getText() );
       return res_code >= 200 && res_code <= 300;
    }

}
