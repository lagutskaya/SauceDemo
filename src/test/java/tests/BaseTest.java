package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.AllureUtils;
import utils.TestListener;

import java.time.Duration;
import java.util.HashMap;

@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    String user = System.getProperty("user");
    String password = System.getProperty("password");

    CheckoutPage checkoutPage;
    SoftAssert softAssert = new SoftAssert();


    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-notification");
            chromeOptions.addArguments("--headless");
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOptions);
        }
        if ((browser.equalsIgnoreCase("firefox"))) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }

        context.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        System.out.println(user);
        System.out.println(password);
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDown(ITestResult result) {
        if (result.FAILURE == result.getStatus()) {
            AllureUtils.takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
