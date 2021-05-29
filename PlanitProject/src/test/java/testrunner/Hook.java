package testrunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import webinteractivies.DriverFactory;

@RunWith(Cucumber.class)	
public class Hook {
	
	@AfterClass
    public static void tearDown(){
		if(DriverFactory.getDriver()!=null)
			new DriverFactory().destroyDriver();
	}
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
	

}
