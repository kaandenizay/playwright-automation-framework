package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@regression",  // @smoke and not @ignore
        plugin = {"pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-report.html"}
)
public class CucumberTests extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios(); // Provide data for the tests, enabling parallel execution
    }

}
