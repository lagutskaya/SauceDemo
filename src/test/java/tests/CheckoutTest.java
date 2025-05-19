package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkValidDataForm() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();

        checkoutPage.clickCheckoutButton();
        checkoutPage.clickContinueButton();
        checkoutPage.fillOrderForm();

        String isPaymentInformationVisible = driver.findElement((checkoutPage.PAYMENT_INFORMATION)).getText();
        assertEquals(isPaymentInformationVisible, "Sauce Labs Bolt T-Shirt",
                "Пользователь не перешел на вторую страницу оформления заказа");
    }

    @Test
    public void checkRequiredNameField() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();

        checkoutPage.clickCheckoutButton();
        checkoutPage.clickContinueButton();
        checkoutPage.fillOrderForm();

        Boolean errorIsDisplayed = driver.findElement(checkoutPage.ERROR).isDisplayed();
        assertTrue(errorIsDisplayed, "Контроль на обязательность заполнения поля 'Name' не сработал");
    }

}
