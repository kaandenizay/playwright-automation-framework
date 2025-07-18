package pages;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import util.Utils;

public class LoginPage extends Utils {

    private String alertText;

    public LoginPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void typeUsername(String username) {
        fillField("Username", username);
    }

    public void typePassword(String password) {
        fillField("Password", password);
    }

    public void clickOnLoginButton() {
        getPage().onceDialog(dialog -> {
            alertText = dialog.message();
            dialog.accept();
        });
        Locator loginButton = getPage().locator("#login-button");
        waitAndClickByLocator(loginButton);
    }

    public void verifyAlertText(String message) {
        Assert.assertEquals(alertText, message, "The alert text does not match the expected text");
    }

}
