package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        driver.get(BASE_URL + "inventory.html");
        return this;
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара с имененем: {product}")
    public ProductsPage addItemToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Нажатие на кнопку корзины")
    public ProductsPage openCart() {
        driver.findElement(CART_BUTTON).click();
        return this;
    }

    @Step("Фильтрация товаров на странице Products A-Z")
    public ProductsPage filterItemsFromAToZ() {
        driver.findElement(FILTER).click();
        driver.findElement(FILTER_A_Z).click();
        return this;
    }

    public ProductsPage isOpened(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART_BUTTON));
        return this;
    }
}

