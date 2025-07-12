package pages;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import org.testng.Assert;
import util.Utils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactUsPage extends Utils {

    public ContactUsPage(BrowserManager browserManager) {
        super(browserManager);
    }

    public void fillFirstName(String firstName) {
        fillField("First Name", firstName);
    }

    public void fillLastName(String lastName) {
        fillField("Last Name", lastName);
    }

    public void fillEmailAddress(String email) {
        fillField("Email Address", email);
    }

    public void typeComment(String comment) {
        fillField("Comments", comment);
    }

    public void clickOnSubmitButton() {
        clickByRole("BUTTON", "SUBMIT");
    }

    public void verifySuccessfulContent(String message){
        //browserManager.getPage().waitForSelector("#contact_reply h1", new Page.WaitForSelectorOptions().setTimeout(5000));
        Locator locator = getPage().locator("#contact_reply h1");
        assertThat(locator).isVisible();
        assertThat(locator).hasText(message);
    }

    public void verifyUnsuccessfulContent(String message){
        String messages = getPage().locator("body").textContent();
        Assert.assertTrue(messages.contains(message));
    }
}
