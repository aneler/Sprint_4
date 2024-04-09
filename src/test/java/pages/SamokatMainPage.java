package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ScooterUtils;

import java.time.Duration;

public class SamokatMainPage {
    private WebDriver driver;
    //кнопка согласиться с использованием cookie
    private By acceptCookiesBtn = By.id("rcc-confirm-button");
    //кнопка заказать расположенная в header страницы
    private By headerOrderBtn = By.xpath("//div[contains(@class, 'Header')]/button[text()='Заказать']");
    //кнопка заказать расположенная в body страницы
    private By bodyOrderBtn = By.xpath("//div[contains(@class, 'Home_FinishButton')]/button[text()='Заказать']");
    //текст вопроса
    private String question = "accordion__heading-";
    //текст ответа
    private String answer = "accordion__panel-";

    //согласиться с использованием cookie
    public void acceptCookies(){
        ScooterUtils.isElementVisible(driver, acceptCookiesBtn, Duration.ofSeconds(3));
        driver.findElement(acceptCookiesBtn).click();
    }
    public SamokatMainPage(WebDriver driver){
        this.driver = driver;
    }
    //кликнуть кнопку заказать в header страницы
    public void clickHeaderOrderBtn(){
        ScooterUtils.isElementVisible(driver, headerOrderBtn, Duration.ofSeconds(3));
        driver.findElement(headerOrderBtn).click();
    }
    //кликнуть кнопку заказать в body страницы
    public void clickBodyOrderBtn(){
        ScooterUtils.isElementVisible(driver, bodyOrderBtn, Duration.ofSeconds(3));
        driver.findElement(bodyOrderBtn).click();
    }
    //кликнуть в вопрос чтобы его развернуть
    public void openAnswer(String id){
        driver.findElement(By.id(question + id)).click();
    }
    //метод получает текст вопроса
    public String getQuestion(String id){
        return driver.findElement(By.id(question + id)).getText();
    }
    //метод получает текст ответа
    public String getAnswer(String id){
        return driver.findElement(By.id(answer + id)).getText();
    }

}
