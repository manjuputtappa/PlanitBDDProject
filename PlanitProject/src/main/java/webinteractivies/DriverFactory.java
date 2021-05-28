package webinteractivies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	
	private static String browser ="chrome";
	private static WebDriver driver =null;
	
	private void initialiseDriver() {
		if(driver==null){
			createDriverInstance();
		}
	}
	
	public DriverFactory() {
		initialiseDriver();
	}
	
	public static String getBrowser() {
		return browser;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void getBrowser(String browser) {
		DriverFactory.browser=browser;
	}

	public static void setDriver(WebDriver driver) {
		DriverFactory.driver=driver;
	}
	
	private void createDriverInstance() {
		switch(getBrowser().toLowerCase()) {
		case "chrome":
			driver=createChromeDriver();
			break;
		default:
			driver=createChromeDriver();
		}
		
	}
	
	private WebDriver createChromeDriver() {
		WebDriver newWebDriver = null;		
		String browserPath="I://Data//MANJU//Automation Setup//WorkSpace//PlanitTesting//driver//chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", browserPath);
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("test-type");
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("user-data-dir=");
		capabilities.setCapability("chrome.binary",chromeOptions);
		capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
		
		newWebDriver= new ChromeDriver();
		newWebDriver.manage().deleteAllCookies();
		return newWebDriver;
	}	
	
	public static void quitDriver() {
		driver.close();
		driver.quit();		
	}
	
	public void destroyed() {
		if(driver!=null)
			driver.quit();
		driver = null;
	}
}
