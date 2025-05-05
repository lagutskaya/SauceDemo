package tests;

import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void checkAddToCartFunctional() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt");
        productsPage.addItemToCart("Sauce Labs Bike Light");
        productsPage.openCart();
    }
}
