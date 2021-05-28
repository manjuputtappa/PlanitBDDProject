package webinteractivies;

import org.openqa.selenium.WebElement;

import engine.IAccessor;

public class Validate {

	public String getTextOf(String elementName) {
		
		WebElement webElement = null;
		String redText="";
		try {
			webElement=IAccessor.webElementsAccessor.getWebElementValueOf(elementName);
			
			if(webElement.getText().length() !=0) {
				redText =webElement.getText();
			}else {
				redText=retryGet(webElement);
			}
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
		return redText;
		}
		
	}

	private String retryGet(WebElement webElement) {
		String retValue="";
		if(webElement.getText().length() != 0) {
			retValue=webElement.getText();
		}else {
			for(int i=0;i<5;i++) {
				try {
					if(webElement.getText().length()!=0) {
						retValue=webElement.getText();
					}else {
						Thread.sleep(1000);
						DriverFactory.getDriver().navigate().refresh();
					}
				}catch(Exception e) {
						e.getStackTrace();
					}
				}
			}
		return retValue;
	}
}
