package webinteractivies;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {
	
	public static final long TIMEOUT_SECONDS=20;
	public static final long POLLING_INTERVAL_MILLS=500;
	public static final int STALE_ELEMENT_RETRY_COUNT=20;
	public static final String SCROLL_WITH_JS="arguments[0].scrollIntoView(true)";
	private static String browser ="chrome";
	private static WebDriver driver =null;
	
	public static String getBrowser() {
		return browser;
	}
	
	public static void getBrowser(String browser) {
		DriverFactory.browser=browser;
	}

	public static void setDriver(WebDriver driver) {
		DriverFactory.driver=driver;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public DriverFactory() {
		initialiseDriver();
	}
	
	public static FluentWait<WebDriver> getFluentWait() {
		return new WebDriverWait(getDriver(), DriverFactory.TIMEOUT_SECONDS).pollingEvery(Duration.ofMillis(DriverFactory.POLLING_INTERVAL_MILLS));				
	}
	
	private void initialiseDriver() {
		if(driver==null){
			createDriverInstance();
		}
	}
	
	private void createDriverInstance() {
		switch(getBrowser().toLowerCase()) {
		case "chrome":
			driver=createChromeDriver();
			break;
		default:
			driver=createChromeDriver();
			break;
		}
		
	}
	
	private WebDriver createChromeDriver() {
		WebDriver newWebDriver = null;		
		ChromeOptions chromeOptions = new ChromeOptions();
		String browserPath="I://Data//MANJU//Automation Setup//WorkSpace//PlanitTesting//driver//chromedriver.exe";		
						
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("test-type");
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("user-data-dir=");		
		
		System.setProperty("webdriver.chrome.driver", browserPath);
		newWebDriver= new ChromeDriver();
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.binary",chromeOptions);
		capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
				
		newWebDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		newWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		newWebDriver.manage().deleteAllCookies();
		return newWebDriver;
	}		
	
	public void destroyDriver() {
		if(driver!=null)
			driver.quit();
		driver = null;
	}
	
	public static void quitDriver() {
		driver.close();
		driver.quit();		
	}
}
