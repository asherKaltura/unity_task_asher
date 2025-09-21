package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private By emailInput = By.name("email");
    private By passwordInput = By.name("password");
    private By loginBtn = By.xpath("//button[text()='Login']");

    public void login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginBtn);
    }
}
