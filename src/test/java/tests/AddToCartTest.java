package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину",
            description = "Проверка функционала добавления товара в корзину",
            priority = 2,
            groups = "Cart Page")
    @Epic("Корзина")
    @Feature("Добавление товара в корзину")
    @Story("Отображение товара в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Полина Лагуцкая")
    @Description("Проверка добавления товара в корзину")
    @Link(name = "документация", url = "https://www.saucedemo.com/")
    @TmsLink("TMS-1")
    @Issue("TMS-2")
    public void checkAddToCartFunctional() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt");
        productsPage.addItemToCart("Sauce Labs Bike Light");
        productsPage.openCart();
    }
}

