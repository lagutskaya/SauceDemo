package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private static final By TITLE = By.cssSelector("[data-test = title]");
    private static final String ADD_TO_CART_PATTERN =
            "//*[text() = '%s']/ancestor::div[@class = 'inventory_item']//button";
    private static final By CART_BUTTON = By.cssSelector("[data-test='shopping-cart-link']");
    public final By FILTER = By.cssSelector("[data-test='product-sort-container']");
    public final By FILTER_A_Z = By.cssSelector("[value='za']");

    @Step("Открытие страницы Products")
    public ProductsPage open() {
        log.info("Product page is opened");
        driver.get(BASE_URL + "inventory.html");
        return this;
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара с имененем: {product}")
    public ProductsPage addItemToCart(String product) {
        log.info("Item added to cart");
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Нажатие на кнопку корзины")
    public ProductsPage openCart() {
        log.info("Cart is opened");
        driver.findElement(CART_BUTTON).click();
        return this;
    }

    @Step("Фильтрация товаров на странице Products A-Z")
    public ProductsPage filterItemsFromAToZ() {
        log.info("Products are filtered");
        driver.findElement(FILTER).click();
        driver.findElement(FILTER_A_Z).click();
        return this;
    }

    public ProductsPage isOpened() {
        log.info("Products page is visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART_BUTTON));
        return this;
    }
}

