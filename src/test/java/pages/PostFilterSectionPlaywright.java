package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PostFilterSectionPlaywright extends BasePagePlaywright {
    private Locator filterContentInput;
    private Locator filterTitleInput;
    private Locator filterBtn;
    private Locator applyBtn;
    private Locator resetBtn;

    public PostFilterSectionPlaywright(Page page) {
        super(page);
        filterContentInput = page.locator("[name='filter-content']");
        filterTitleInput = page.locator("[name='filter-title']");
        filterBtn = page.locator("a[data-css='Post-filter-button']");
        applyBtn = page.locator("button[data-css='Post-filter-drawer-button-apply']");
        resetBtn = page.locator("button[data-css='Post-filter-drawer-button-reset']");
    }

    public void open() {
        click(filterBtn);
    }

    public void setContentFilter(String content) {
        type(filterContentInput, content);
    }

    public void setTitleFilter(String title) {
        type(filterTitleInput, title);
    }

    public void applyFilter(String title, String content) {
        open();
        setTitleFilter(title);
        setContentFilter(content);
        apply();
    }

    public void apply() {
        click(applyBtn);
    }

    public void reset() {
        click(resetBtn);
    }
}
