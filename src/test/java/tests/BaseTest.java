package tests;

import lombok.extern.log4j.Log4j2;
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

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    CheckoutPage checkoutPage;
    SoftAssert softAssert = new SoftAssert();

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            log.info("Tests run in chrome browser");
            ChromeOptions chromeOptions = new ChromeOptions();
            if (System.getProperty("incognito", "true").equals("true")) {
                chromeOptions.addArguments("--incognito");
            }
            chromeOptions.addArguments("--disable-notification");
            if (System.getProperty("headless", "true").equals("true")) {
                chromeOptions.addArguments("--headless");
            }
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--disable-gpu");
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOptions);
        }
        if ((browser.equalsIgnoreCase("firefox"))) {
            log.info("Tests run in firefox browser");
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
