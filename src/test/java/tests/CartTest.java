package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Test
    public void checkCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.openCart();

        assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"),
                "Товар не добавлен в корзину");
        assertEquals(cartPage.getProductFromCart(0),
                "Sauce Labs Backpack",
                "Товар не добавлен в корзину");

        assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"), 29.99);
    }

    @Test
    public void removeProductsFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Onesie");
        productsPage.openCart();

        cartPage.remove();

        Boolean isRemoved = driver.findElement(By.cssSelector("[data-test='inventory-item-name']")).isDisplayed();

        Assert.assertFalse(isRemoved, "Товар не удален из корзины");
    }
}