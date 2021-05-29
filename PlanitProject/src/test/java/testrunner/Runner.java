package testrunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)				
@CucumberOptions(features="src/test/feature",
                 glue={"stepdefinition","testrunner"},
                 tags={"@TEST"},                 
                 plugin={"pretty","html:target/cucumber-report-html",
                		 "json:target/report.json",
                		 "junit:target/cucumber-reports/report.xml",
                		 },
                 monochrome=true)	

public class Runner 				
{		
	
}
