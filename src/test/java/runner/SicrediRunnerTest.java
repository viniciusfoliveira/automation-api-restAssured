package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "steps",
        strict = true,
        tags = "@todos",
        plugin = { "pretty", "html:target/cucumber-html-report" },
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class SicrediRunnerTest {
}
