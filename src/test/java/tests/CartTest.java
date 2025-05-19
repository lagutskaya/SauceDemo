package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class CartTest extends BaseTest {

    @Test
    public void checkCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();

        softAssert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"),
                "Товар не добавлен в корзину");
        softAssert.assertEquals(cartPage.getProductFromCart(0),
                "Sauce Labs Backpack",
                "Товар не добавлен в корзину");

        softAssert.assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        softAssert.assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"), 29.99);
        softAssert.assertAll();
    }

    @Test
    public void removeProductsFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Onesie");
        productsPage.openCart();

        cartPage.remove();

        Boolean isRemoved = driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).isDisplayed();

        assertFalse(isRemoved, "Товар не удален из корзины");
    }
}