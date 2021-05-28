package testrunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)				
@CucumberOptions(features="src/test/feature",
                 glue={"stepdefinition"},
                 tags={"@TEST"},
                 plugin={"html:target/cucumber-report-html","json:target/cucumber.json","junit:target/cucumber-reports/cucumber.xml"})	

public class Runner 				
{		
	
}
