package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PostPagePlaywright extends BasePagePlaywright {

    private Locator titleInput;
    private Locator contentInput;
    private Locator activeCheckbox;
    private Locator postMenu;
    private Locator createNewBtn;
    private Locator publishedCheckbox;
    private Locator publisherInput;
    private Locator saveBtn;
    private Locator statusSelect;
    private Locator statusContainer;
    private Locator publishedLabel;
    private Locator addNewItemBtn;
    private Locator postsTable;

    private PostFilterSectionPlaywright filterSection;

    public PostPagePlaywright(Page page) {
        super(page);
        titleInput = page.locator("#title");
        contentInput = page.locator("#content");
        activeCheckbox = page.locator("input[name='active']");
        postMenu = page.locator("//div[normalize-space(text())='Post']");
        createNewBtn = page.locator("a[data-testid='action-new']");
        publishedCheckbox = page.locator("#published");
        publisherInput = page.locator("input#react-select-6-input");
        saveBtn = page.locator("button[data-testid='button-save']");
        statusSelect = page.locator("//input[starts-with(@id,'react-select') and contains(@id,'-input')]");
        publishedLabel = page.locator("label[for='published']");
        addNewItemBtn = page.locator("button[data-testid='someJson-add']");
        postsTable = page.locator("[data-css='Post-table']");
        statusContainer = page.locator("#treact-select-2-input");
        filterSection = new PostFilterSectionPlaywright(page);
    }

    public void createPost(String title, String content, String publisher) {
        click(postMenu);
        click(createNewBtn);
        type(titleInput, title);
        type(contentInput, content);
        changeStatus(PostStatus.ACTIVE);
        setPublished(true);
        type(publisherInput, publisher);
        click(page.locator("//div[text()='" + publisher + "']"));
        click(addNewItemBtn);
        click(saveBtn);
    }

    public PostFilterSectionPlaywright filter() {
        return filterSection;
    }

    public void setPublished(boolean shouldBeChecked) {
        boolean isChecked = publishedCheckbox.isChecked();
        if (isChecked != shouldBeChecked) {
            click(publishedLabel);
        }
    }

    public void changeStatus(PostStatus status) {
        //clickWithOutWait(statusContainer);
       // type(statusSelect, status.name());
       // click(page.locator("//div[text()='" + status.name() + "']"));
       // Locator statusSelect = page.locator("#react-select-2-input");
        //selectReact(page, "react-select-2-input", "ACTIVE");
        Locator input = page.locator("input[id^='react-select'][id$='-input']").nth(0);
        input.click();
        // מקליד ערך
       // input.fill("הערך שאתה רוצה");

// 2. Type the value
     //   page.keyboard().type(status.name()); // e.g., "ACTIVE"

// 3. Click the option
        page.locator("#react-select-5-listbox .css-12r18xa-MenuList").type(status.name());


     //page.locator("[role='option']:has-text('ACTIVE')").click();



    }
    public String getPostStatus() {
        return postsTable
                .locator("tbody tr:first-child td:has-text('ACTIVE'), " +
                        "tbody tr:first-child td:has-text('REMOVED')")
                .innerText()
                .trim();
    }
    public enum PostStatus {
        ACTIVE,
        REMOVED
    }
}
