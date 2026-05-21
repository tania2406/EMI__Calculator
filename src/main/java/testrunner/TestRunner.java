package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = {
			    "src/main/resources/features/EMI_AValidation.feature",
			    "src/main/resources/features/EMI_BFunctional.feature",
			    "src/main/resources/features/EMI_CReport.feature"
			},
    glue = "stepsdefinitions",
    plugin =
{
        "pretty",
        "html:target/cucumber-report.html",        
        //"json:target/cucumber.json",               
//        "junit:target/cucumber.xml"              
    }

)

public class TestRunner extends AbstractTestNGCucumberTests {
}