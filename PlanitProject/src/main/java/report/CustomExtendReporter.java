package report;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.Scenario;


public class CustomExtendReporter {

	private ExtentHtmlReporter extentHtmlReporter;
	private ExtentReports extentReports;

	public void CustomExtentReporter(String reportLocation){
	extentHtmlReporter = new ExtentHtmlReporter(new File(reportLocation));
	extentReports = new ExtentReports();
	extentReports.attachReporter(extentHtmlReporter );
	}
	
	public void createTest(Scenario scenario,String screenShotFile){
		if(scenario != null){
			String testName= getScenarioTitle(scenario);
			switch (scenario.getStatus()){
			case PASSED:
			extentReports.createTest(testName).pass("Passed");
			break;
			
			case FAILED:
			extentReports.createTest(testName).pass("Failed").addScreenCaptureFromPath(getSscreenshotLocation(screenShotFile));
			break;
			
			default:
			extentReports.createTest(testName).skip("Skipped");
			break;
			}
		}
	}
		public void writeToReport(){
		if(extentReports != null){
		extentReports.flush();
		}
		}

		private String getSscreenshotLocation(String aLoaction){
		return "file:///" + aLoaction.replaceAll("\\","//");
		}

		private String getScenarioTitle(Scenario scenario){
		return scenario.getName().replaceAll(" ", "");
		}


}
