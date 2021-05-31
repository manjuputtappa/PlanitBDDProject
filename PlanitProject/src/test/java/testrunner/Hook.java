package testrunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.gherkin.model.Scenario;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import report.CustomExtendReporter;
import webinteractivies.DriverFactory;

@RunWith(Cucumber.class)	
public class Hook {
	
	private static CustomExtendReporter customExtendReporter;
	private static boolean isReporterRunning;
	
	@Before
	public void browserStep() {
		 String urlVal="http://jupiter.cloud.planittesting.com";
		 new DriverFactory();	
		 DriverFactory.getDriver().navigate().to(urlVal);
		 DriverFactory.getDriver().manage().window().maximize();	
	}
	
	@After
	public void TearDown() {
		if(DriverFactory.getDriver()!=null)
			new DriverFactory().destroyDriver();
	}
	
	@Before
	public void beforeScenario(Scenario scenario) {
		 if(!isReporterRunning) {
//			 customExtendReporter= new CustomExtendReporter("");
//			 isReporterRunning true;
		 }
	}
}
