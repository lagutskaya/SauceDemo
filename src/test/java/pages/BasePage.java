package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.PropertyReader;

import java.time.Duration;

public abstract class BasePage {

    WebDriver driver;
    public static final String BASE_URL = PropertyReader.getProperty("baseURL");
    public WebDriverWait wait;
    public Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }
}

