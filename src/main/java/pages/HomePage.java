package pages;

import browser.BrowserManager;
import util.Utils;

public class HomePage extends Utils {

    public HomePage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void navigateToHomePage() {
        navigateTo("https://webdriveruniversity.com/");
    }

    public void clickAndNavigateToContactUsPage() {
        setPageChangeAfterClickByRole("LINK","CONTACT US Contact Us Form");
//        getPage().bringToFront();
    }

    public void clickAndNavigateToLoginPage() {
        setPageChangeAfterClickByRole("LINK","LOGIN PORTAL Login Portal Are");
    }
}
