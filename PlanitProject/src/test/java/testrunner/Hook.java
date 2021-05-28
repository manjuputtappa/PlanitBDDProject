package testrunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;
import webinteractivies.DriverFactory;

@RunWith(Cucumber.class)	
public class Hook {

	@After
	public void TearDown() {
		if(DriverFactory.getDriver()!=null)
			new DriverFactory().destroyed();
	}
	
	@AfterClass
    public static void tearDown(){
		if(DriverFactory.getDriver()!=null)
			new DriverFactory().destroyed();
	}

}
