package tests;

import java.util.Collection;
import java.util.Arrays;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.SamokatMainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqTestClass {
    private DriverFactory driverFactory = new DriverFactory();
    private String id;
    private String expectedQuestion;
    private String expectedAnswer;
    private SamokatMainPage objMainPage;

    @Before
    public void setUp() {
        driverFactory.initDriver();
    }

    public FaqTestClass(String id, String expectedQuestion, String expectedAnswer) {
        this.id = id;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }
    @Test
    public void testQuestions() {
        WebDriver driver = driverFactory.getDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        objMainPage = new SamokatMainPage(driver);
        objMainPage.acceptCookies();
        objMainPage.openAnswer(id);
        String actualQuestion = objMainPage.getQuestion(id);
        String actualAnswer = objMainPage.getAnswer(id);
        assertEquals("Фактический вопрос не соответствует ожидаемому: ", expectedQuestion, actualQuestion);
        assertEquals("Фактический ответ не соответствует ожидаемому: ", expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        if(driverFactory.getDriver() != null) {
            driverFactory.getDriver().quit();
        }
    }
}
