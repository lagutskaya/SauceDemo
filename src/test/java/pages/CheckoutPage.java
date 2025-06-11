package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
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

    @Step("Нажатие на 'Checkout' кнопку")
    public CheckoutPage clickCheckoutButton() {
        log.info("Checkout button clicked");
        driver.findElement(CHECKOUT_BUTTON).click();
        return this;
    }

    @Step("Нажатие на 'Continue' кнопку")
    public CheckoutPage clickContinueButton() {
        log.info("Continue button clicked");
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Заполнение формы оформления заказа")
    public CheckoutPage fillOrderForm() throws InterruptedException {
        log.info("The form is complete");
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_FIELD));
        driver.findElement(FIRST_NAME_FIELD).sendKeys("Polina");
        driver.findElement(LAST_NAME_FIELD).sendKeys("Q");
        driver.findElement(ZIP_POSTAL_CODE_FIELD).sendKeys("123345");

        Thread.sleep(4000);
        driver.findElement(CHECKOUT_BUTTON).click();
        return this;
    }

    public CheckoutPage isOpened() {
        log.info("Checkout page is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_FIELD));
        return this;
    }
}

