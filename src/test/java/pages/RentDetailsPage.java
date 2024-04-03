package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class RentDetailsPage {
    private WebDriver driver;
    //дата доставки самоката
    private By deliveryAppointment = By.xpath("//div[@class='react-datepicker__input-container']/input");
    //срок аренды самоката
    private By rentalPeriod = By.xpath("//div[contains(@class, 'Dropdown-root')]//span[@class='Dropdown-arrow']");
    //дропдаун с вариантами срока аренды самоката
    private By rentalPeriodDropdown = By.xpath("//div[contains(@class, 'Dropdown-root')]/div[contains(@class, 'Dropdown-menu')]");
    //поле для комментария курьеру
    private By commentForTheCourier = By.xpath("//input[@class=\"Input_Input__1iN_Z Input_Responsible__1jDKN\" and @placeholder=\"Комментарий для курьера\"]");
    //кнопка возврата к персональным данным заказчика
    private By backBtn = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[text()='Назад']");
    //кнопка подтверждения заказа самоката
    private By makeOrderBtn = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");

    public RentDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для выбора даты доставки самоката
    public void setDeliveryAppointment(Integer daysToIncrement) {
        Instant testDate = Instant.now().plus(daysToIncrement, ChronoUnit.DAYS);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = dateFormat.format(Date.from(testDate));
        driver.findElement(deliveryAppointment).sendKeys(formattedDate);
    }

    //метод для выбора времени аренды самоката
    public void setRentalPeriod(String period){
        driver.findElement(rentalPeriod).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement rentalDropdown = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown));
        WebElement option = rentalDropdown.findElement(By.xpath(".//div[text()='" + period + "']"));
        option.click();
    }

    //метод для выбора цвета самоката
    public void setScooterColor(String color){
        driver.findElement(By.id(color)).click();
    }

    //метод устанавливает комментарий для курьера
    public void setCommentForTheCourier(String comment){
        driver.findElement(commentForTheCourier).sendKeys(comment);
    }

    //метод для заполнения информации по аренде самоката
    public void fillRentalDetailsAndSubmit(Integer daysToIncrement,
                                           String rentalPeriod,
                                           String color,
                                           String comment){
        setDeliveryAppointment(daysToIncrement);
        setRentalPeriod(rentalPeriod);
        setScooterColor(color);
        setCommentForTheCourier(comment);
        driver.findElement(makeOrderBtn).click();
    }

}
