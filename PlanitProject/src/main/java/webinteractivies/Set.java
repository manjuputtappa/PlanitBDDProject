package webinteractivies;

import org.openqa.selenium.WebElement;

import engine.IAccessor;

public class Set {
	public void enterValueInTextbox(String value, String webElementName) throws InterruptedException {
		WebElement textBoxWebElement = IAccessor.webElementsAccessor.getWebElementValueOf(webElementName);
		Thread.sleep(500);
		textBoxWebElement.sendKeys(value);
		Thread.sleep(500);
	}

}
