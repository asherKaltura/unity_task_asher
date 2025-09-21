package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import utils.DriverManager;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;

public class BasePage {
    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public void doAction(By locator, Consumer<WebElement> action) {
        WebElement element = wait.until(drv -> {
            WebElement el = drv.findElement(locator);
            return (el.isDisplayed() && el.isEnabled()) ? el : null;
        });
        action.accept(element);
    }

    public void doAction(WebElement element, Consumer<WebElement> action) {
        wait.until(drv -> element.isDisplayed() && element.isEnabled());
        action.accept(element);
    }

    public void click(By locator) {
        doAction(locator, WebElement::click);
    }

    public void click(WebElement element) {
        doAction(element, WebElement::click);
    }

    public void type(WebElement element, String text) {
        doAction(element, el -> {
            el.clear();
            el.sendKeys(text);
        });
    }

    public void type(By locator, String text) {
        doAction(locator, el -> {
            el.clear();
            el.sendKeys(text);
        });
    }
    public String getText(By locator) {
        final String[] text = {""};
        doAction(locator, el -> text[0] = el.getText());
        return text[0];
    }
    public  void selectDropdownByVisibleText(WebElement element, String text) {
        doAction(element, el -> {
            Select select = new Select(el);
            select.selectByVisibleText(text);
        });
    }

    public  void selectDropdownByValue(WebElement element, String value) {
        doAction(element, el -> {
            Select select = new Select(el);
            select.selectByValue(value);
        });
    }
}
