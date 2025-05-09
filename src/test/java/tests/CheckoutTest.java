package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkValidDataForm() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();

        driver.findElement(checkoutPage.FIRST_NAME_FIELD).sendKeys("Test");
        driver.findElement(checkoutPage.LAST_NAME_FIELD).sendKeys("Test");
        driver.findElement(checkoutPage.ZIP_POSTAL_CODE_FIELD).sendKeys("123345");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPage.CHECKOUT_BUTTON));

        driver.findElement(checkoutPage.CHECKOUT_BUTTON).click();

        Boolean isPaymentInformationVisible = driver.findElement((checkoutPage.PAYMENT_INFORMATION)).isDisplayed();

        assertTrue(isPaymentInformationVisible,
                "Пользователь не перешел на вторую страницу оформления заказа");
    }

    @Test
    public void checkRequiredNameField() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();

        driver.findElement(checkoutPage.LAST_NAME_FIELD).sendKeys("QWERTY");
        driver.findElement(checkoutPage.ZIP_POSTAL_CODE_FIELD).sendKeys("12345");
        driver.findElement(checkoutPage.CHECKOUT_BUTTON).click();

        Boolean errorIsDisplayed = driver.findElement(checkoutPage.ERROR).isDisplayed();

        assertTrue(errorIsDisplayed, "Контроль на обязательность заполнения поля 'Name' не сработал");
    }

}
