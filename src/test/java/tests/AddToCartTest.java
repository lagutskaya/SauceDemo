package tests;

import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину",
            description = "Проверка функционала добавления товара в корзину",
            priority = 2,
            groups = "Cart Page")
    public void checkAddToCartFunctional() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt");
        productsPage.addItemToCart("Sauce Labs Bike Light");
        productsPage.openCart();
    }
}
