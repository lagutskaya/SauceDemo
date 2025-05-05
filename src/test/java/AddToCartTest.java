import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddToCartTest extends BaseTest {
    @Test
    public void checkAddToCartFunctional() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//input[@data-test='username']"))
                .sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@data-test='password']"))
                .sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();

        String products = driver.findElement(By.xpath("//span[@data-test='title']")).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(products, "Products", "Логин не выполнен");

        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']")).click();
        String removeButton = driver.findElement(By.xpath(
                "//button[@data-test='remove-sauce-labs-backpack']")).getText();
        softAssert.assertEquals(removeButton, "Remove", "Товар не добавлен в корзину");

        driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']")).click();
        String checkoutButton = driver.findElement(By.xpath("//button[@data-test='checkout']")).getText();
        softAssert.assertEquals(checkoutButton, "Checkout", "Товар не добавлен в корзину");

        String nameOfItem = driver.findElement(By.xpath(
                "//div[@data-test='inventory-item-name']")).getText();
        softAssert.assertEquals(nameOfItem, "Sauce Labs Backpack",
                "Добавленный товар в корзине не соответствует товару, который был добавлен в корзину");

        String priceForItem = driver.findElement(By.xpath(
                "//div[@data-test='inventory-item-price']")).getText();
        softAssert.assertEquals(priceForItem, "$29.99", "Стоимость товара не совпадает");

        softAssert.assertAll();
    }
}
