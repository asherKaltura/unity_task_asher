package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PublisherPagePlaywright extends BasePagePlaywright {

    private Locator addPublisherBtn;
    private Locator nameInput;
    private Locator saveBtn;
    private Locator publisherBtn;
    private Locator happyFolderMenu;
    private Locator createNewPublisherBtn;
    private Locator publisherNameInput;
    private Locator publisherEmailInput;
    private Locator publisherSaveBtn;

    public PublisherPagePlaywright(Page page) {
        super(page);
        addPublisherBtn = page.locator("button[data-css='add-publisher']");
        nameInput = page.locator("input[name='name']");
        saveBtn = page.locator("button[data-css='save']");
        publisherBtn = page.locator("//div[text()='Publisher']");
        happyFolderMenu = page.locator("//div[text()='Happy Folder']");
        createNewPublisherBtn = page.locator("//a[text()='Create new']");
        publisherNameInput = page.locator("#name");
        publisherEmailInput = page.locator("#email");
        publisherSaveBtn = page.locator("[data-testid='button-save']");
    }

    public void createPublisher(String name, String email) {
        click(happyFolderMenu);
        click(publisherBtn);
        click(createNewPublisherBtn);
        type(publisherNameInput, name);
        type(publisherEmailInput, email);
        click(publisherSaveBtn);
    }
}
