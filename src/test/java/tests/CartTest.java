package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;


public class CartTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину",
            description = "Проверка функционала добавления товара в корзину",
            priority = 2,
            groups = "Cart Page")
    @Epic("Корзина")
    @Feature("Добавление товара в корзину")
    @Story("Отображение товара в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Полина Лагуцкая")
    @Link(name = "документация", url = "https://www.saucedemo.com/")
    @TmsLink("TMS-10")
    @Issue("TMS-11")
    public void checkCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();

        softAssert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"),
                "Товар не добавлен в корзину");
        softAssert.assertEquals(cartPage.getProductFromCart(0),
                "Sauce Labs Backpack",

                "SO BAAAAAD");

        softAssert.assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"), 29.99);
        softAssert.assertAll();
    }
}
