package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(	plugin = {	"html:html-report/cucumber.html"
							},
					glue ="seleniumframeworkdesign.stepDefinitions",
					monochrome = true, 
					features = "src/test/java/cucumber", 
					tags = "@Regression"
			)
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
