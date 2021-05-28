package webelements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import webinteractivies.DriverFactory;

class WebElements {
	String page;
	String name;
	String value;
	String findBy;	
	
	public WebElements(String webPage, String webElementName, String elementFindBy, String webElementValue) {
		this.page=webPage;
		this.name=webElementName;
		this.findBy=elementFindBy;
		this.value=webElementValue;
	}
}

public class WebElementsAccessor {
	
	private static WebElementsAccessor instance;
	private String activeElementName;
		
	public WebElementsAccessor() {
		
	}
	
	public static synchronized WebElementsAccessor getInstance() {
		if(instance == null)
			instance = new WebElementsAccessor();
		return instance;
	}	
	
	String webElementsPath="";
	static Set<WebElements> allElementsSet = new HashSet<>();//Try making this non static
	private String activePage = "";
	
	private String getWebElementsPath() {
		return webElementsPath;
	}
	
	public void setWebElementsPath(String webElementsPath) {
		this.webElementsPath=webElementsPath;
	}
	
	public WebElement getWebElementValueOf(String elementName) {
		WebElement webElement = getWebElementValue(elementName);
		if(webElement == null) {
			for(int i=0;i<2;i++) {
				webElement = getWebElementValue(elementName);
				if(webElement == null) {
					break;
				}
			}
		}
		return webElement;
	} 
		
	String activeElementFindby;
	String activeElementValue;	
	
	private void setActiveElementValue(String activeElementValue) {
		this.activeElementValue=activeElementValue;
	}
	
	private String getActiveElementValue() {
		return this.activeElementValue;
	}
				
	private void setActiveElementFindby(String activeElementFindBy) {
		this.activeElementFindby=activeElementFindBy;
	}
	
	private String getActiveElementFindBy() {
		return this.activeElementFindby;
	}
	
	private void setActiveElementName(String activeElementName) {
		this.activeElementName=activeElementName;
	}
	
	public void readAndLoadWebElements() {
		List<String> webElementFolders = new ArrayList<>();
		webElementFolders.add(getWebElementsPath());
		
		webElementFolders.forEach(folder -> {
			if(Paths.get(folder).toFile().exists()) {
				try (Stream<Path> path = Files.walk(Paths.get(folder))) {
					path.filter(Files::isRegularFile).map(Path::toFile).forEach(
							file -> {
								allElementsSet.addAll(loadWebElements(file));
							}
					);
					path.close();
				}catch(IOException exception) {
					exception.printStackTrace();
				}
			}
		});
	}
	
	private Set<WebElements> loadWebElements(File webElementFile) {
		List<WebElements> webElementsList = new ArrayList<>();
		try(Stream<String> webElementStream = Files.lines(Paths.get(webElementFile.toString()))){
			webElementStream
			.filter(line -> line.contains(","))
			.forEach(webElementEntry -> webElementsList.add(new WebElements(webElementFile.getName().replace(".csv", ""), webElementEntry.split(",")[0], webElementEntry.split(",")[1], getValueOnSplit(webElementEntry))));
			webElementStream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		Set<WebElements> webElementsSet = webElementsList.stream().collect(Collectors.toSet());
		return webElementsSet;
	}
	
	private String getValueOnSplit(String webElementEntry) {
		String[] arr = webElementEntry.split(",");
		String eleVal = "";
		if(arr.length == 3)
			eleVal=arr[2];
		if(arr.length >3) {
			eleVal = webElementEntry;
			for(int i=0; i<2; i++) {
				eleVal = eleVal.replace(arr[i]+",","");				
			}
		}
		return eleVal;
	}
	
	public boolean isElementPresent(String webElementName) throws InterruptedException {
		boolean elePresent = false;
		By byElement = null;
		List<WebElement> activeWebElementLst;
		setActiveElementValueAndFindByFor(webElementName);
		byElement=getFindBy(webElementName);
		activeWebElementLst = DriverFactory.getDriver().findElements(byElement);
		if(activeWebElementLst.size()!=0)
			elePresent = true;
		else {
			int i=0;
			while(i<80) {
				activeWebElementLst = DriverFactory.getDriver().findElements(byElement);
				if(activeWebElementLst.size()==0) {
					Thread.sleep(100);
					i++;
				}else {
					if(activeWebElementLst.size()!=0) {
						elePresent = true;
						break;
					}
				}
			}
		}
		return elePresent;
	}

	private By getFindBy(String webElementName) {
		By findBy = null;
		String byMethod = getActiveElementFindBy();
		String webElementValue = getActiveElementValue();
		switch(byMethod.toUpperCase()) {
		case "CLASS_NAME":
			findBy=By.className(webElementValue);
			break;
		case "XPATH":
			findBy=By.xpath(webElementValue);
			break;
		case "CSS":
			findBy=By.cssSelector(webElementValue);
			break;
		case "ID":
			findBy=By.id(webElementValue);
			break;
		case "NAME":
			findBy=By.name(webElementValue);
			break;
		case "LINK_NAME":
			findBy=By.linkText(webElementValue);
			break;
		case "PARTIAL_LINK_TEXT":
			findBy=By.partialLinkText(webElementValue);
			break;
		case "TAG_NAME":
			findBy=By.tagName(webElementValue);
			break;
		}
		return findBy;
	}
	
	private void setActiveElementValueAndFindByFor(String webElementName) {
		final String[] webElementValue = {""};
		final String[] webElementFindBy = {""};
		
		if (!(webElementName.contains(";"))){
			allElementsSet.forEach(webElement -> {
				if(webElement.page.equalsIgnoreCase(activePage) && webElement.name.equalsIgnoreCase(webElementName)) {
					webElementValue[0]=webElement.value;
					webElementFindBy[0]=webElement.findBy;
					setActiveElementValue(webElement.value);
					setActiveElementFindby(webElement.findBy);
				}
			});
			setActiveElementName(webElementName);
		}else {
			webElementFindBy[0]=webElementName.split(";")[0];
			webElementValue[0]=webElementName.split(";")[1];
			setActiveElementValue(webElementValue[0]);
			setActiveElementFindby(webElementFindBy[0]);
			if(webElementName.split(";").length==3)
				setActiveElementName(webElementName.split(";")[2]);
			else
				setActiveElementName("");
		}
	}
	
	private WebElement getWebElementValue(String webElementName) {
		WebElement activeWebElement;
		By findBy=null;
		setActiveElementValueAndFindByFor(webElementName);
		findBy = getFindBy(webElementName);
		activeWebElement = findActiveWebElement(findBy);
		return activeWebElement;		
	}
	
	
	
	private WebElement findActiveWebElement(By element) {
		List<WebElement> activeWebElementLst;
		WebElement activeWebElement = null;
		try {
			activeWebElementLst = DriverFactory.getDriver().findElements(element);
			if(activeWebElementLst.size() != 0) {
				activeWebElement=activeWebElementLst.get(0);
			}else {
				int i=0;
				while(i<80) {
					activeWebElementLst = DriverFactory.getDriver().findElements(element);
					if(activeWebElementLst.size() == 0) {
						Thread.sleep(100);
						i++;
					}else {
						if(activeWebElementLst.size() != 0) {
							activeWebElement=activeWebElementLst.get(0);
							break;
						}
				}
			}
				if(activeWebElement == null) {
					activeWebElement=DriverFactory.getDriver().findElement(element);
				}
			}
		}catch(Exception e) {
					e.getStackTrace();
				}
		return activeWebElement;
	}
}