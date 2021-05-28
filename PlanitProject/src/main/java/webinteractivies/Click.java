package webinteractivies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import engine.IAccessor;

public class Click {

	public void button(String buttonName) throws InterruptedException {
		WebElement buttonElement = null;
		buttonElement = IAccessor.webElementsAccessor.getWebElementValueOf(buttonName);
		buttonElement.click();
		Thread.sleep(1000);
	}
	
	public void link(String linkName) throws InterruptedException {
		WebElement linkElement = null;
		linkElement = IAccessor.webElementsAccessor.getWebElementValueOf(linkName);
		linkElement.click();
		Thread.sleep(1000);
	}
}
