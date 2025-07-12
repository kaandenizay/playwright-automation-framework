package step_definitions;

import browser.BrowserManager;
import io.cucumber.java.*;

public class Hooks {

    private final BrowserManager browserManager;

    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }
    //Runs once before all tests starts
   @BeforeAll
   public static void beforeAll() {
       System.out.println("Executing Tests Suite Before All");
   }

    //Runs once after all tests are done
   @AfterAll
    public static void afterAll() {
       System.out.println("Finished Tests Suite After All");
   }

   //Runs before each tests
   @Before
    public void setUp(){
        browserManager.setUp();
   }

   //Runs after each tests
   @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = browserManager.takeScreenshot();
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        browserManager.tearDown();

   }
}
