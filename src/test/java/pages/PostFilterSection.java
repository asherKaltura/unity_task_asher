package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostFilterSection extends BasePage {


    @FindBy(name ="filter-content")
    private WebElement filterContentInput;

    @FindBy(name ="filter-title")
    private WebElement filterTitleInput;
    @FindBy(css = "a[data-css='Post-filter-button']")
    private WebElement filterBtn;

    @FindBy(name = "filter-content")
    private WebElement contentInput;


    @FindBy(css = "button[data-css='Post-filter-drawer-button-apply']")
    private WebElement applyBtn;


    @FindBy(css = "button[data-css='Post-filter-drawer-button-reset']")
    private WebElement resetBtn;

    public void open() {
        PageFactory.initElements(driver, this); // <- חובה!
        doAction(filterBtn, WebElement::click);
    }

    public void setContentFilter(String content) {
        doAction(filterContentInput, el -> {
            el.clear();
            el.sendKeys(content);
        });
    }
    public void setTitleFilter(String title) {
        doAction(filterTitleInput, el -> {
            el.clear();
            el.sendKeys(title);
        });
    }
    public void apply() {
        doAction(applyBtn, WebElement::click);
    }

    public void reset() {
        doAction(resetBtn, WebElement::click);
    }

}
