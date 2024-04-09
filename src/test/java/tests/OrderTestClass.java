package tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.After;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.OrderConfirmationPage;
import pages.PersonDetailsPage;
import pages.RentDetailsPage;
import pages.SamokatMainPage;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTestClass {

    private DriverFactory driverFactory = new DriverFactory();
    //определяет, кнопка находится в header или body страницы
    private String buttonType;
    private String firstName;
    private String lastName;
    private String address;
    private String subwayStation;
    private String phoneNumber;
    private Integer daysToIncrement;
    private String rentalPeriod;
    private String scooterColor;
    private String commentForTheCourier;


    @Before
    public void setUp() {
        driverFactory.initDriver();
    }
    public OrderTestClass(String buttonType,
                          String firstNameNew,
                          String lastNameNew,
                          String addressNew,
                          String subwayStationNew,
                          String phoneNumberNew,
                          Integer daysToIncrementNew,
                          String rentalPeriodNew,
                          String scooterColorNew,
                          String commentForTheCourierNew){
        this.buttonType = buttonType;
        this.firstName = firstNameNew;
        this.lastName = lastNameNew;
        this.address = addressNew;
        this.subwayStation = subwayStationNew;
        this.phoneNumber = phoneNumberNew;
        this.daysToIncrement = daysToIncrementNew;
        this.rentalPeriod = rentalPeriodNew;
        this.scooterColor = scooterColorNew;
        this.commentForTheCourier = commentForTheCourierNew;
    }

    //тестовые данные для выполнения testScooterOrder()
    //каждый набор содержит информацию о том, какая кнопка заказа должна быть нажата, в header или body
    //и данные для заполнения формы заказа
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"header", "Лиса", "Патрикеевна", "улица Усачёва, 29к3", "Парк культуры", "+1234567897",
                        10, "сутки", "black", "Оставьте, пожалуйста, самокат около курятника"},
                {"body", "Змей", "Горыныч", "Зубовский бульвар, 2с5", "Спортивная", "+1234567897",
                        3, "пятеро суток", "grey", "жду у огненной пещеры"},
        });
    }

    @Test
    public void testScooterOrder() {
        WebDriver driver = driverFactory.getDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        SamokatMainPage objMainPage = new SamokatMainPage(driver);
        objMainPage.acceptCookies();

        switch (buttonType) {
            case "header":
                objMainPage.clickHeaderOrderBtn();
                break;
            case "body":
                objMainPage.clickBodyOrderBtn();
                break;
            default:
                throw new IllegalArgumentException("Тип кнопки не поддерживается: " + buttonType);
        }

        PersonDetailsPage objPersonDetails = new PersonDetailsPage(driver);
        objPersonDetails.fillPersonalDataAndProceed(firstName, lastName, address, subwayStation, phoneNumber);

        RentDetailsPage objRentDetails = new RentDetailsPage(driver);
        objRentDetails.fillRentalDetailsAndSubmit(daysToIncrement, rentalPeriod, scooterColor, commentForTheCourier);

        OrderConfirmationPage objOrderConfirmation = new OrderConfirmationPage(driver);
        boolean orderConfirmationWindowDisplayed = objOrderConfirmation.isConfirmationWindowVisible();
        assertEquals("Диалог подтверджения заказа не появился: ", true, orderConfirmationWindowDisplayed);
        objOrderConfirmation.clickConfirmOrder();
        boolean orderConformationMessageVisible = objOrderConfirmation.isConfirmationMsgVisible();
        assertEquals("Подтверждение заказа не появилось: ", true, orderConformationMessageVisible);
    }

    @After
    public void tearDown() {
        driverFactory.getDriver().quit();
    }
}
