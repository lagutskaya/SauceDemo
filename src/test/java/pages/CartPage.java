package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Корзина")
    public void open() {
        log.info("Cart page opened");
        driver.get(BASE_URL + "/cart.html");
    }

    public boolean isProductInCart(String product) {
        log.info("Product added in cart");
        return driver.findElement(By.xpath(String.format("//div[@class='cart_item']//*[text()='%s']", product)))
                .isDisplayed();
    }

    public String getProductFromCart(int index) {
        log.info("Information about added products is taken");
        return driver.findElements(By.cssSelector(".inventory_item_name"))
                .get(index)
                .getText();
    }

    public ArrayList<String> getProductsName() {
        List<WebElement> allProductsElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProductsElements) {
            names.add(product.getText());
        }
        return names;
    }

    public double getProductPrice(String product) {
        return Double.parseDouble(driver.findElement(
                        By.xpath(String.format(
                                "//*[text() = '%s']/ancestor::div[@class='cart_item']//" +
                                        "*[@class = 'inventory_item_price']", product)))
                .getText().replace("$", ""));
    }

    public final By REMOVE_BUTTON = By.xpath("//button[@id='remove-sauce-labs-backpack']");

    @Step("Нажатие на кнопку 'удалить' на странице корзины")
    public void remove() {
        log.info("Product is removed from cart");
        driver.findElement(REMOVE_BUTTON).click();
    }
}

