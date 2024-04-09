package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ScooterUtils;

import java.time.Duration;

//класс описывает модальный диалог для подтверждения заказа
public class OrderConfirmationPage {
    private WebDriver driver;
    //окно подтверждения заказа
    private By confirmationWindow = By.xpath("//div[contains(@class, 'Order_Modal')]");
    //кнопка подтвердить заказ
    private By confirmOrder = By.xpath("//div[contains(@class, 'Order_Modal')]//button[text()='Да']");
    //кнопка отменить заказ
    private By cancelOrder = By.xpath("//div[contains(@class, 'Order_Modal')]//button[text()='Нет']");
    //текст подтверждения заказа
    private By confirmationMessage = By.xpath("//div[contains(@class, 'Order_Modal')]/div[contains(@class, 'Order_ModalHeader')]");

    public OrderConfirmationPage(WebDriver driver){
        this.driver = driver;
    }
    //в методе выполнятся нажатие на кнопку подтвердить заказ
    public void clickConfirmOrder(){
        driver.findElement(confirmOrder).click();
    }
    //в методе выполнятся нажатие на кнопку отмены заказа
    public void clickCancelOrder(){
        driver.findElement(cancelOrder);
    }
    //метод возвращает, отображается ли диалог подтверждения заказа
    public boolean isConfirmationWindowVisible(){
        return ScooterUtils.isElementVisible(driver, confirmationWindow, Duration.ofSeconds(5));
    }
    //метод возвращает true или false, отображается ли сообщение о подтверждении заказа
    public boolean isConfirmationMsgVisible(){
        String confirmationText = driver.findElement(confirmationMessage).getText();
        return ScooterUtils.isElementVisible(driver, confirmationMessage, Duration.ofSeconds(5)) && confirmationText.contains("Заказ оформлен");
    }

}
