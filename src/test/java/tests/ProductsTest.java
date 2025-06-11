package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Test(testName = "Фильтрация товаров по значению A-Z на странице Products")
    @Epic("Продуктовый каталог")
    @Feature("Фильтрация товаров")
    @Story("Фильтрация товаров по значению A-Z")
    @Severity(SeverityLevel.MINOR)
    @Owner("Полина Лагуцкая")
    @Description("Фильтрация товаров по значению A-Z на странице Products")
    @Flaky
    @Link(name = "документация", url = "https://www.saucedemo.com/")
    @TmsLink("TMS-4")
    @Issue("TMS-5")
    public void checkFilterFunctional() {
        loginPage.open()
                .isOpened()
                .login("standard_user", "secret_sauce");


        productsPage.isOpened()
                .filterItemsFromAToZ();

        String filteredItem = driver.findElement(By.xpath(
                "//*[text()='Test.allTheThings() T-Shirt (Red)']")).getText();
        assertEquals(filteredItem, "Test.allTheThings() T-Shirt (Red)",
                "Товары не отсортированы по значению Z - A");
    }

    @Test(testName = "Добавление 3 товаров в корзину")
    @Epic("Продуктовый каталог")
    @Feature("Добавление товаров в корзину")
    @Story("Добавление > 1 товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Полина Лагуцкая")
    @Description("Добавление 3 товаров в корзину со страницы Products")
    @Flaky
    @Link(name = "документация", url = "https://www.saucedemo.com/")
    @TmsLink("TMS-5")
    @Issue("TMS-6")
    public void add3ProductsInCart() {
        loginPage.open()
                .isOpened()
                .login("standard_user", "secret_sauce");

        productsPage.isOpened()
                .addItemToCart("Sauce Labs Backpack")
                .addItemToCart("Sauce Labs Bike Light")
                .addItemToCart("Sauce Labs Bolt T-Shirt")
                .openCart();

        softAssert.assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        softAssert.assertTrue(cartPage.getProductsName().contains("Sauce Labs Bike Light"));
        softAssert.assertTrue(cartPage.getProductsName().contains("Sauce Labs Bolt T-Shirt"));

        softAssert.assertAll();
    }
}

