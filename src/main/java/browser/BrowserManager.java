package browser;

import com.microsoft.playwright.*;
import io.cucumber.java.Scenario;
import org.testng.internal.TestMethodContainer;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
                        .setArgs(getBrowserOptions())
                        .setSlowMo(500)
        ));

        browserContext.set(browser.get().newContext(new Browser.NewContextOptions().setViewportSize(null)));

        browserContext.get().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true));

        page.set(browserContext.get().newPage());
        page.get().setDefaultNavigationTimeout(configuration().navigationTimeout());
        page.get().setDefaultTimeout(configuration().actionTimeout());
        logger.info("Playwright setup complete.");
    }

    public void tearDown(Scenario scenario) {
        try {
            logger.info("Tearing down playwright...");
            if (getPage() != null) getPage().close();
            getBrowserContext().tracing().stop(
                    new Tracing.StopOptions()
                            .setPath(Paths.get( "target/traces/" + scenario.getName().replaceAll("[^a-zA-Z0-9]","") + "-trace.zip"))
            );
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

    private List<String> getBrowserOptions() {
        List<String> browserOptions = new ArrayList<>();

        // Performance & Stability
        browserOptions.add("--no-sandbox");
        browserOptions.add("--disable-dev-shm-usage");
        browserOptions.add("--disable-gpu");
        browserOptions.add("--disable-extensions");

        // Browser Behavior
        browserOptions.add("--window-size=1920,1080");
        browserOptions.add("--start-maximized");
        browserOptions.add("--disable-notifications");
        browserOptions.add("--disable-popup-blocking");
        browserOptions.add("--disable-infobars");
        browserOptions.add("--disable-translate");


        return browserOptions;
    }

}
