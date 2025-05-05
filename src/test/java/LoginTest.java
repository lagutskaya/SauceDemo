import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void checkLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String title = driver.findElement(By.xpath("//span[@data-test='title']")).getText();
        Assert.assertEquals(title, "Products", "Логин не выполнен");

    }

    @Test
    public void checkLoginWithEmptyValue() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();

        String title = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(title, "Epic sadface: Password is required", "Логин не выполнен");

    }
}
