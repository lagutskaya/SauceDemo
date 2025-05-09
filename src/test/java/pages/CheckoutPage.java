package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public final By FIRST_NAME_FIELD = By.cssSelector("[data-test='firstName']");
    public final By LAST_NAME_FIELD = By.cssSelector("[data-test='lastName']");
    public final By ZIP_POSTAL_CODE_FIELD = By.cssSelector("[data-test='postalCode']");
    public final By CHECKOUT_BUTTON = By.xpath("//*[@data-test='checkout']");
    public final By ERROR = By.cssSelector("[data-test='error']");
    public final By PAYMENT_INFORMATION = By.xpath("//*[@data-test='payment-info-label']");
}
