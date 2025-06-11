package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By USER_NAME_FIELD = By.id("user-name"),
            PASSWORD_FIELD = By.id("password"),
            LOGIN_BUTTON = By.id("login-button"),
            ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    @Step("Открытие страницы login page")
    public LoginPage open() {
        log.info("Login page is opened");
        driver.get(BASE_URL);
        return this;
    }

    @Step("Вход в систему с именем пользователя: {user} и паролем: {password}")
    public LoginPage login(String user, String password) {
        log.info("The user has authorized in the system with credentials : {user} {password}");
        driver.findElement(USER_NAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public LoginPage isOpened() {
        log.info("Login page is visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(USER_NAME_FIELD));
        return this;
    }

}

