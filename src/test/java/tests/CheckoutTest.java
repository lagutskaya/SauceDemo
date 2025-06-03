package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest {

    @Test(testName = "Заполнение валидными данными формы для оформления заказа")
    @Epic("Оформление заказа")
    @Feature("Оформление заказа на странице Checkout")
    @Story("Переход на 2-ую страницу оформления заказа после заполнения формы заказа")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Полина Лагуцкая")
    @Description("Оформление заказа на странице Checkout")
    @Flaky
    @Link(name = "документация", url = "https://www.saucedemo.com/")
    @TmsLink("TMS-2")
    @Issue("TMS-3")
    public void checkValidDataForm() throws InterruptedException {
        loginPage.open()
                .login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt")
                .openCart();

        checkoutPage.clickCheckoutButton()
                .clickContinueButton()
                .fillOrderForm();

        String isPaymentInformationVisible = driver.findElement((checkoutPage.PAYMENT_INFORMATION)).getText();
        assertEquals(isPaymentInformationVisible, "Sauce Labs Bolt T-Shirt",
                "Пользователь не перешел на вторую страницу оформления заказа");
    }

    @Test(testName = "Обязательность заполнения поля 'Name' на странице Checkout")
    @Epic("Оформление заказа")
    @Feature("Форма оформления заказа")
    @Story("Обязательность заполнения поля 'Name' в форме оформления заказа")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Полина Лагуцкая")
    @Description("Обязательность заполнения поля 'Name' в форме оформления заказа")
    @Flaky
    @Link(name = "документация", url = "https://www.saucedemo.com/")
    @TmsLink("TMS-3")
    @Issue("TMS-4")
    public void checkRequiredNameField() throws InterruptedException {
        loginPage.open()
                .isOpened()
                .login("standard_user", "secret_sauce");

        productsPage.isOpened()
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .openCart();

        checkoutPage.isOpened()
                .clickCheckoutButton()
                .clickContinueButton()
                .fillOrderForm();

        Boolean errorIsDisplayed = driver.findElement(checkoutPage.ERROR).isDisplayed();
        assertTrue(errorIsDisplayed, "Контроль на обязательность заполнения поля 'Name' не сработал");
    }
}
