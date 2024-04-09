package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private WebDriver driver;

    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            initFirefox();
        } else {
            initChrome();
        }
    }

    private void initFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions().configureFromEnv();
        driver = new FirefoxDriver(options);
    }

    private void initChrome() {
        System.setProperty("webdriver.chrome.driver", "/Users/elena/seleniumdrivers/chromedriver_mac_arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome 114.app/Contents/MacOS/Google Chrome");
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
