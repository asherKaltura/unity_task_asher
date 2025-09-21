package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.FakerUtils;

import java.util.List;

public class PostPage extends BasePage {

    @FindBy(id = "title")
    private WebElement titleInput;

    @FindBy(id = "content")
    private WebElement ContentInput;

    @FindBy(css = "input[name='active']")
    private WebElement activeCheckbox;

    @FindBy(xpath = "//div[normalize-space(text())='Post']")
    private WebElement postMenu;

    @FindBy(css = "a[data-testid='action-new']")
    private WebElement createNewBtn;

    @FindBy(id = "published")
    private WebElement publishedCheckbox;
    @FindBy(css = "input#react-select-6-input")
    private WebElement publisherInput;
    @FindBy(css = "button[data-testid='button-save']")
    private WebElement saveBtn;

    @FindBy(xpath = "//input[starts-with(@id,'react-select') and contains(@id,'-input')]")
    private WebElement statusSelect;

    @FindBy(css = "label[for='published']")
    private WebElement publishedLabel;

    @FindBy(css = "button[data-testid='someJson-add']")
    private WebElement addNewItemBtn;
    @FindBy(id = "someJson.1.number")
    private WebElement numberInput;

    @FindBy(css = "[data-css='Post-table']")
    private WebElement postsTable;
    @FindBy(css = "a[data-css='Post-filter-button']")
    private WebElement filterBtn;
    private PostFilterSection filterSection = new PostFilterSection();

    @FindBy(css = "a[data-testid='actions-dropdown']")
    private WebElement menu;

    public void createPost(String title, String content, String publisher) {
        PageFactory.initElements(driver, this); // <- חובה!
        click(postMenu);
        click(createNewBtn);
        type(titleInput, title);
        type(ContentInput, content);
        changeStatus(PostStatus.ACTIVE);
        setPublished(true);
        type(publisherInput, publisher);
        By publisherDiv = By.xpath(String.format("//div[text()='%s']", publisher));
        doAction(publisherDiv, WebElement::click);
        doAction(addNewItemBtn, WebElement::click);
        setJsonField(0, FieldType.NUMBER.getFieldName(), FakerUtils.getNumber());
        clickSave();
    }

    public PostFilterSection filter() {
        return filterSection;
    }

    public void setPublished(boolean shouldBeChecked) {
        doAction(publishedLabel, el -> {
            boolean isChecked = publishedCheckbox.isSelected();
            if (isChecked != shouldBeChecked) {
                el.click();
            }
        });
    }

    public void setJsonField(int index, String fieldName, String value) {
        By locator = By.name("someJson." + index + "." + fieldName);
        type(locator, value);
    }

    public void changeStatus(PostStatus postStatus) {

        type(statusSelect, postStatus.name());
        By statusSelect1 = By.xpath(String.format("//div[text()='%s']", postStatus.name()));
        doAction(statusSelect1, WebElement::click);

    }
    public enum FieldType {
        STRING("string"),
        NUMBER("number"),
        BOOLEAN("boolean");

        private final String fieldName;

        FieldType(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }

    public String getValueByHeader(String headerName) {

        WebElement table = wait.until(drv -> postsTable);


        List<WebElement> headers = table.findElements(By.cssSelector("thead tr td, thead tr th"));
        int colIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(headerName)) {
                colIndex = i + 1;
                break;
            }
        }

        if (colIndex == -1) throw new RuntimeException("Header not found!");

        int finalColIndex = colIndex;
        WebElement cell = wait.until(drv ->
                table.findElement(By.cssSelector("tbody tr:nth-of-type(1) td:nth-of-type(" + finalColIndex + ")"))
        );

        return cell.getText().trim();
    }

    public void clickAction(PostAction action) {

        doAction(By.cssSelector("a[data-testid='actions-dropdown']"), WebElement::click);
        doAction(By.cssSelector("a[data-testid='" + action.getTestId() + "']"), WebElement::click);

    }

    public void clickSave() {
        click(saveBtn);
    }

    public enum PostAction {
        SHOW("action-show"),
        EDIT("action-edit"),
        DELETE("action-delete");

        private final String testId;

        PostAction(String testId) {
            this.testId = testId;
        }

        public String getTestId() {
            return testId;
        }
    }

    public enum PostStatus {
        ACTIVE,
        REMOVED;

    }
}
