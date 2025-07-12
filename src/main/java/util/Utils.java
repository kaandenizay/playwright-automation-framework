package util;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Utils {

    private final BrowserManager browserManager;

    public Utils(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    protected BrowserManager getBrowserManager() {
        return browserManager;
    }

    protected Page getPage() {
        return getBrowserManager().getPage();
    }

    public void navigateTo(String url) {
        getPage().navigate(url);
    }

    public void clickByRole(String role, String name) {
        Locator element = getPage().getByRole(AriaRole.valueOf(role.toUpperCase()),
                new Page.GetByRoleOptions().setName(name));
        element.click();
    }

    public void waitAndClickBySelector(String selector) {
        getPage().waitForSelector(selector,
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        getPage().click(selector);
    }

    public void waitAndClickByLocator(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        locator.click();
    }

    public void setPageChangeAfterClickByRole(String role, String name) {
        getBrowserManager().setPage(getBrowserManager().getBrowserContext().waitForPage(() -> {
            clickByRole(role, name);}));
    }

    public void fillField(String placeholder, String value) {
        getPage().getByPlaceholder(placeholder).fill(value);
    }

}
