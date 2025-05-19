package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class CartTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину",
            description = "Проверка функционала добавления товара в корзину",
            priority = 2,
            groups = "Cart Page")
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
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"), 29.99);

        softAssert.assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"), 29.99);
        softAssert.assertAll();
    }
}