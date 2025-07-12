package step_definitions;

import browser.BrowserManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import util.Utils;

import static org.testng.Assert.assertEquals;


public class LoginSteps{

    private final LoginPage loginPage;

    public LoginSteps(BrowserManager browserManager) {
        loginPage = new LoginPage(browserManager);
    }

    @And("I type username as {string}")
    public void iTypeUsernameAs(String username) {
        loginPage.typeUsername(username);
    }

    @And("I type password as {string}")
    public void iTypePasswordAs(String password) {
        loginPage.typePassword(password);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("I should be see the {string} alert message")
    public void iShouldBeSeeTheAlertMessage(String message) {
        loginPage.verifyAlertText(message);
    }
}