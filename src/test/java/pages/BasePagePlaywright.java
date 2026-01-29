package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePagePlaywright {
    protected Page page;

    public BasePagePlaywright(Page page) {
        this.page = page;
    }

    // ===== Click =====
    public void click(Locator locator) {
        locator.waitFor();
        locator.click();
    }
    public void clickWithOutWait(Locator locator) {
        locator.click();
    }
    // ===== Type =====
    public void type(Locator locator, String text) {
        locator.waitFor();
        locator.fill(text); // clear + type
    }

    // ===== Get Text =====
    public String getText(Locator locator) {
        locator.waitFor();
        String text = locator.textContent();
        return text != null ? text.trim() : "";
    }

    // ===== Select Dropdown =====
    public void selectByLabel(Locator locator, String label) {
        locator.waitFor();
        locator.selectOption(label);
    }

    public void selectByValue(Locator locator, String value) {
        locator.waitFor();
        locator.selectOption(value);
    }


    public static void selectReact(Page page, String inputId, String value) {
        page.locator("div:has(input#" + inputId + ")").click();
        page.keyboard().type(value);
        page.keyboard().press("Enter");
    }
}
