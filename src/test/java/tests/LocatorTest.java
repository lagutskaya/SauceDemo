package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class LocatorTest extends BaseTest {
    @Test
    public void checkLocatorsLoginPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name"));
        driver.findElement(By.name("user-name"));
        driver.findElement(By.className("error-message-container"));
        driver.findElement(By.tagName("div"));
    }

    @Test
    public void checkLocatorsProductsPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.linkText("Sauce Labs Backpack"));
        driver.findElement(By.partialLinkText("Sauce"));
        driver.findElement(By.xpath("//div[@data-test='inventory-item-desc']"));
        driver.findElement(By.xpath(
                "(//div[text()='carry.allTheThings() with the sleek, " +
                        "streamlined Sly Pack that melds uncompromising style " +
                        "with unequaled laptop and tablet protection.'])"));
        driver.findElement(By.xpath(
                "//div[@data-test='inventory-item-desc'" +
                        " and contains(text(), 'carry.allTheThings')]"));
        driver.findElement(By.xpath(
                "//div[@data-test='inventory-item-desc' " +
                        "and contains(text(), 'carry.allTheThings')]" +
                        "/ancestor::div[contains(@class, 'inventory_item')][1]"));
        driver.findElement(By.xpath(
                "//div[contains(@class, 'inventory_item')]" +
                        "//descendant::div[@data-test='inventory-item-desc' " +
                        "and contains(text(), 'carry.allTheThings')]"));
        driver.findElement(By.xpath(
                "//div[@data-test='inventory-item-desc' " +
                        "and contains(text(), 'carry.allTheThings')]" +
                        "/following::div[contains(@class, 'inventory_item')][1]"));
        driver.findElement(By.xpath(
                "//div[@data-test='inventory-item-desc' " +
                        "and contains(text(), 'carry.allTheThings')]" +
                        "/parent::div[contains(@class, 'inventory_item')]"));
        driver.findElement(By.xpath(
                "//div[contains(@class, 'inventory_item')]" +
                        "//div[@data-test='inventory-item-desc'" +
                        " and contains(text(), 'carry.allTheThings')]" +
                        "/preceding::div[contains(@class, 'inventory_item')][1]"));
        driver.findElement(By.xpath(
                "//div[@data-test='inventory-item-desc' and contains(text(), 'carry.allTheThings')]"));

        driver.findElement(By.cssSelector(".inventory_item_desc"));
        driver.findElement(By.cssSelector(".inventory_item .inventory_item_name"));
        driver.findElement(By.cssSelector("#inventory_container"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("div.pricebar"));
        driver.findElement(By.cssSelector("div[data-test='inventory-item-name']"));
        driver.findElement(By.cssSelector("div[data-test~='inventory-item-name']"));
        driver.findElement(By.cssSelector("[data-test|='inventory']"));
        driver.findElement(By.cssSelector("[data-test^='inventory']"));
        driver.findElement(By.cssSelector("[class$='item']"));
        driver.findElement(By.cssSelector("[data-test*='item']"));
    }
}
