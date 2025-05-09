package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @Test
    public void checkFilterFunctional() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        driver.findElement(productsPage.FILTER).click();
        driver.findElement(productsPage.FILTER_A_Z).click();

        String filteredItem = driver.findElement(By.xpath(
                "//*[text()='Test.allTheThings() T-Shirt (Red)']")).getText();
        assertEquals(filteredItem, "Test.allTheThings() T-Shirt (Red)",
                "Товары не отсортированы по значению Z - A");
    }

    @Test
    public void add3ProductsInCart(){
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.addItemToCart("Sauce Labs Bike Light");
        productsPage.addItemToCart("Sauce Labs Bolt T-Shirt");

        productsPage.openCart();

        assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Bike Light"));
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Bolt T-Shirt"));
    }
}
