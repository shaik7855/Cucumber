package MTMAutomation.DispatchTest.cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
features="src/test/resources/features",
glue="MTMAutomation.DispatchTest.stepDefinitions",
tags = "@verifyAllColumnsVisible",
plugin = {"pretty", "html:target/cucumber-reports.html"},
monochrome=true)
public class CucumberRunner extends AbstractTestNGCucumberTests {

}
