package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PublisherPage extends BasePage {

    @FindBy(css = "button[data-css='add-publisher']")
    private WebElement addPublisherBtn;

    @FindBy(css = "input[name='name']")
    private WebElement nameInput;

    @FindBy(css = "button[data-css='save']")
    private WebElement saveBtn;

    @FindBy(xpath = "//div[text()='Publisher']")
    private WebElement publisherBtn;

    @FindBy(xpath = "//div[text()='Happy Folder']")
    private WebElement happyFolderMenu;

    @FindBy(xpath = "//a[text()='Create new']")
    private WebElement createNewPublisherBtn;

    @FindBy(id = "name")
    private WebElement publisherNameInput;

    @FindBy(id = "email")
    private WebElement publisherEmailInput;

    @FindBy(css = "[data-testid='button-save']")
    private WebElement publisherSaveBtn;

    public void createPublisher(String name,String email) throws InterruptedException {
        PageFactory.initElements(driver, this); // <- חובה!
        click(happyFolderMenu);
        click(publisherBtn);
        click(createNewPublisherBtn);
        type(publisherNameInput, name);
        type(publisherEmailInput, email);
        click(publisherSaveBtn);

    }
}
