package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

	    features = "src/main/resources/features/EMI_Excel.feature",
//    		"src/main/resources/features/EMI_Report.feature",
//    		"src/main/resources/features/EMI_Validation.feature"},
    glue = "stepsdefinitions",
    plugin = {"pretty"}
)

public class TestRunner extends AbstractTestNGCucumberTests {
}