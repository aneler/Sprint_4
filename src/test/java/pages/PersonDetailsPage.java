package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonDetailsPage {
    private WebDriver driver;
    //имя заказчика
    private By firstName = By.xpath("//input[@class=\"Input_Input__1iN_Z Input_Responsible__1jDKN\" and @placeholder=\"* Имя\"]");
    //фамилия заказчика
    private By lastName = By.xpath("//input[@class=\"Input_Input__1iN_Z Input_Responsible__1jDKN\" and @placeholder=\"* Фамилия\"]");
    //адрес доставки заказа
    private By address = By.xpath("//input[contains(@class, \"Input_Input__1iN_Z Input_Responsible__1jDKN\") and contains(@placeholder, \"Адрес\")]");
    //станция метро
    private By subwayStation = By.xpath("//input[@class=\"select-search__input\" and @placeholder=\"* Станция метро\"]");
    //выпадающий список с доступными станциями метро
    private By stationsList = By.cssSelector(".select-search__select");
    //номер телефона заказчика
    private By phoneNumber = By.xpath("//input[@class=\"Input_Input__1iN_Z Input_Responsible__1jDKN\" and @placeholder=\"* Телефон: на него позвонит курьер\"]");
    //кнопка перехода к следующей части оформления заказа
    private By nextBtn = By.xpath("//div[contains(@class, 'Order_NextButton')]/button");

    public PersonDetailsPage(WebDriver driver){
        this.driver = driver;
    }

    //метод для заполнения личных данных заказчика
    public void fillPersonalDataAndProceed(String firstNameNew, String lastNameNew, String addressNew, String subwayStationNew, String phoneNumberNew){
        driver.findElement(firstName).sendKeys(firstNameNew);
        driver.findElement(lastName).sendKeys(lastNameNew);
        driver.findElement(address).sendKeys(addressNew);
        driver.findElement(subwayStation).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectList = wait.until(ExpectedConditions.visibilityOfElementLocated(stationsList));
        WebElement option = selectList.findElement(By.xpath(".//div[text()='" + subwayStationNew + "']"));
        option.click();

        driver.findElement(phoneNumber).sendKeys(phoneNumberNew);
        driver.findElement(nextBtn).click();

    }
}
