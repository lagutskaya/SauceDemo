package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Test
    public void checkFilterFunctional() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.filterItemsFromAToZ();

        String filteredItem = driver.findElement(By.xpath(
                "//*[text()='Test.allTheThings() T-Shirt (Red)']")).getText();
        assertEquals(filteredItem, "Test.allTheThings() T-Shirt (Red)",
                "Товары не отсортированы по значению Z - A");
    }

    @Test
    public void add3ProductsInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.addItemToCart("Sauce Labs Bike Light");
        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt");

        productsPage.openCart();

        softAssert.assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        softAssert.assertTrue(cartPage.getProductsName().contains("Sauce Labs Bike Light"));
        softAssert.assertTrue(cartPage.getProductsName().contains("Sauce Labs Bolt T-Shirt"));

        softAssert.assertAll();
    }
}
