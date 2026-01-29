package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPagePlaywright extends BasePagePlaywright {
    private Locator emailInput;
    private Locator passwordInput;
    private Locator loginBtn;

    public LoginPagePlaywright(Page page) {
        super(page);
        emailInput = page.locator("input[name='email']");
        passwordInput = page.locator("input[name='password']");
        loginBtn = page.locator("//button[text()='Login']");
    }

    public void login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginBtn);
    }
}
