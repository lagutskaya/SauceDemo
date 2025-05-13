package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Логин валидными данными",
            description = "Проверка логина с валидными данными",
            priority = 1,
            groups = "Login Page")
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
    }

    @Test(testName = "Логин с пустым паролем",
            description = "Негативный кейс на проверку логина с пустым паролем",
            priority = 2,
            groups = "Login Page")
    public void checkLoginWithEmptyValue() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Логин не выполнен");
    }

    @Test(testName = "Логин с невалидным username",
            description = "Негативный кейс на проверку логина с невалидным username",
            priority = 2,
            groups = "Login Page")
    public void checkLoginInvalidUsername() {
        loginPage.open();
        loginPage.login("standard_user1", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Логин не выполнен");
    }

    @DataProvider(name = "Негативные тесты для логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "1243143143", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Негативные тесты для логина")
    public void login(String user, String password, String message) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                message,
                "Unexpected result");
    }
}
