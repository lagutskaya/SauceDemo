package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {
    public final By FIRST_NAME_FIELD = By.id("first-name");
    public final By LAST_NAME_FIELD = By.id("last-name");
    public final By ZIP_POSTAL_CODE_FIELD = By.id("postal-code");
    public final By CHECKOUT_BUTTON = By.xpath("//*[@data-test='checkout']");
    public final By ERROR = By.cssSelector("[data-test='error']");
    public final By PAYMENT_INFORMATION = By.xpath("//*[@data-test='payment-info-label']");
    public final By CONTINUE_BUTTON = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void fillOrderForm() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_FIELD));
        driver.findElement(FIRST_NAME_FIELD).sendKeys("Polina");
        driver.findElement(LAST_NAME_FIELD).sendKeys("Q");
        driver.findElement(ZIP_POSTAL_CODE_FIELD).sendKeys("123345");

        Thread.sleep(4000);
        driver.findElement(CHECKOUT_BUTTON).click();
    }
}
