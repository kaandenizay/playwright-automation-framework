package browser;

import com.microsoft.playwright.*;

import java.util.Arrays;
import java.util.logging.Logger;

import static config.ConfigurationManager.configuration;

public class BrowserManager {

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    private final Logger logger = Logger.getLogger(BrowserManager.class.getName());
    private BrowserType browserType;

    public void setUp() {
        playwright.set(Playwright.create());
        playwright.get().selectors().setTestIdAttribute("data-test");

        String selectedBrowser = System.getProperty("browser");
        if (selectedBrowser == null || selectedBrowser.isEmpty()) {
            selectedBrowser = configuration().browser();
        }

        switch (selectedBrowser.toLowerCase()) {
            case "firefox" -> browserType = playwright.get().firefox();
            case "webkit" -> browserType = playwright.get().webkit();
            default -> browserType = playwright.get().chromium();
        }

        browser.set(browserType.launch(
                new BrowserType.LaunchOptions().setHeadless(configuration().headless())
                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
        ));

        browserContext.set(browser.get().newContext(new Browser.NewContextOptions().setViewportSize(1600, 1200)));
        page.set(browserContext.get().newPage());
        page.get().setDefaultNavigationTimeout(configuration().navigationTimeout());
        page.get().setDefaultTimeout(configuration().actionTimeout());
        logger.info("Playwright setup complete.");
    }

    public void tearDown() {
        try {
            logger.info("Tearing down playwright...");
            if (getPage() != null) getPage().close();
            if (getBrowserContext() != null) getBrowserContext().close();
            if (browser.get() != null) browser.get().close();
            if (playwright.get() != null) playwright.get().close();
            logger.info("Playwright teardown complete.");
        } catch (Exception e) {
            logger.severe("Playwright teardown failed: " + e.getMessage());
        }

    }

    public byte[] takeScreenshot() {
        if (getPage() != null) {
            return getPage().screenshot(new Page.ScreenshotOptions()
                    .setFullPage(true));
        }
        return new byte[0];
    }

    public Page getPage() {
        return page.get();
    }

    public void setPage(Page newPage) {
        page.set(newPage);
    }

    public BrowserContext getBrowserContext() {
        return browserContext.get();
    }

}
