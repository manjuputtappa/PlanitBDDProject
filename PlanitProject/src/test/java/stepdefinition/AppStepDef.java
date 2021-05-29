package stepdefinition;

import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.IWebActions;
import objectrepository.PlanitPage;
import webinteractivies.DriverFactory;
import webinteractivies.RandomDataGenerator;

public class AppStepDef 
{	
	@Given("^launch the planit application$")
	public void launch_the_planit_application() throws Throwable {	    
		 String actualValue=IWebActions.validate.getTextOf(PlanitPage.lnkJupiterToys.get());
		 String expectedValue="Jupiter Toys";
		 if(actualValue.equalsIgnoreCase(expectedValue))
				Assert.assertTrue(true);
			else
				Assert.assertTrue(false);
			System.out.println(actualValue + " page is loaded");
	     Thread.sleep(1000);
	}

	@When("^click on contact page$")
	public void click_on_contact_page() throws Throwable {
		String contactPage=IWebActions.validate.getTextOf(PlanitPage.lnkContactPage.get());
		IWebActions.click.link(PlanitPage.lnkContactPage.get());
		System.out.println(contactPage + " is clicked");
		Thread.sleep(1000);
	}

	@When("^click on submit button$")
	public void click_on_submit_button() throws Throwable {
		String submitButton=IWebActions.validate.getTextOf(PlanitPage.btnSubmit.get());
		IWebActions.click.link(PlanitPage.btnSubmit.get());
		System.out.println(submitButton + " is clicked");
		Thread.sleep(1000);
	}

	@Then("^validate errors of populate mandatory fields are gone$")
	public void validate_errors_of_populate_mandatory_fields_are_gone() throws Throwable {
		String actualValueForeName=IWebActions.validate.getTextOf(PlanitPage.txtForeName.get());
		String expectedValueForeName="Forename is required";
		if(actualValueForeName.equalsIgnoreCase(expectedValueForeName))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		System.out.println(actualValueForeName);
		Thread.sleep(1000);
		
		String actualValueEmailID=IWebActions.validate.getTextOf(PlanitPage.txtEmailID.get());
		String expectedValueEmailID="Email is required";
		if(actualValueEmailID.equalsIgnoreCase(expectedValueEmailID))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		System.out.println(actualValueEmailID);
		Thread.sleep(1000);
		
		String actualValueMessage=IWebActions.validate.getTextOf(PlanitPage.txtMessage.get());
		String expectedValueMessage="Message is required";
		if(actualValueMessage.equalsIgnoreCase(expectedValueMessage))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		System.out.println(actualValueMessage);
		Thread.sleep(1000);
	}
	
	@When("^enter all details in contact page$")
	public void click_on_contact_page_and_enter_all_details() throws Throwable {
		IWebActions.set.enterValueInTextbox(RandomDataGenerator.generate(RandomDataGenerator.valueType.FIRSTNAME), PlanitPage.inpForeName.get());
		IWebActions.set.enterValueInTextbox(RandomDataGenerator.generate(RandomDataGenerator.valueType.LASTNAME), PlanitPage.inpSurname.get());
		IWebActions.set.enterValueInTextbox(RandomDataGenerator.generate(RandomDataGenerator.valueType.EMAIL), PlanitPage.inpEmail.get());
		IWebActions.set.enterValueInTextbox(RandomDataGenerator.generate(RandomDataGenerator.valueType.MOBILEPHONE), PlanitPage.inpTelephone.get());
		IWebActions.set.enterValueInTextbox(RandomDataGenerator.generate(RandomDataGenerator.valueType.RANDOMSTRING), PlanitPage.inpMessage.get());
		Thread.sleep(500);
	}

	@Then("^validate successful submission message on the page$")
	public void validate_successful_submission_message_on_the_page() throws Throwable {
		String actualSuccessfulMessage=IWebActions.validate.getTextOf(PlanitPage.txtSuccessful.get());
		String S1="Thanks ";
		String firstName=RandomDataGenerator.fetchGeneratevalueOf((RandomDataGenerator.valueType.FIRSTNAME));
		String S3=", we appreciate your feedback.";
		String expectedSuccessfulMessage=S1+firstName+S3;
		System.out.println(expectedSuccessfulMessage);
		if(actualSuccessfulMessage.equalsIgnoreCase(expectedSuccessfulMessage))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		Thread.sleep(1000);
	}

	@When("^enter invalid data$")
	public void click_on_contact_page_and_enter_invalid_data() throws Throwable {
		IWebActions.set.enterValueInTextbox(RandomDataGenerator.generate(RandomDataGenerator.valueType.MOBILEPHONE), PlanitPage.inpEmail.get());
		Thread.sleep(500);
	}

	@Then("^validate errors for invalid data$")
	public void validate_errors_for_invalid_data() throws Throwable {
		String actualValueInValidData=IWebActions.validate.getTextOf(PlanitPage.txtIvalidMessage.get());
		String expectedValueInValidData="Please enter a valid email";
		if(actualValueInValidData.equalsIgnoreCase(expectedValueInValidData))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		System.out.println(actualValueInValidData);
		Thread.sleep(1000);
	}

	@When("^click on home page and go to shop page$")
	public void click_on_home_page_and_go_to_shop_page() throws Throwable {
		String startShopping=IWebActions.validate.getTextOf(PlanitPage.btnShopping.get());
		IWebActions.click.button(PlanitPage.btnShopping.get());
		System.out.println(startShopping + " is clicked");
		Thread.sleep(1000);
	}

	@When("^click buy button \"([^\"]*)\" times on \"([^\"]*)\"$")
	public void click_buy_button_times_on_Funny_Cow(int arg1,String arg2) throws Throwable {
		String funnyCow=IWebActions.validate.getTextOf(PlanitPage.lblFunnyCow.get());
		System.out.println(funnyCow+ " item is available");
		IWebActions.click.button(PlanitPage.btnFunnyCow.get());
		IWebActions.click.button(PlanitPage.btnFunnyCow.get());
		Thread.sleep(1000);
	}

	@When("^click buy button \"([^\"]*)\" time on \"([^\"]*)\"$")
	public void click_buy_button_time_on_Fluffy_Bunny(int arg1,String arg2) throws Throwable {
		String fluffyBunny=IWebActions.validate.getTextOf(PlanitPage.lblFluffyBunny.get());
		System.out.println(fluffyBunny+ " item is available");
		IWebActions.click.button(PlanitPage.btnFluffyBunny.get());
		Thread.sleep(1000);
	}

	@When("^click the cart menu$")
	public void click_the_cart_menu() throws Throwable {
		IWebActions.click.button(PlanitPage.btnCart.get());
		Thread.sleep(1000);
	}

	@Then("^verify the items are added in the cart$")
	public void verify_the_items_are_added_in_the_cart() throws Throwable {		
		String fluffyBunny=IWebActions.validate.getTextOf(PlanitPage.lblFluffyBunnyAddedItem.get());
		System.out.println(fluffyBunny + " item added into the cart");
		Thread.sleep(1000);
		String fluffyBunnySubTotal=IWebActions.validate.getTextOf(PlanitPage.lblFluffyBunnySubtotal.get());
		IWebActions.click.button(PlanitPage.lblFluffyBunnyAddedItem.get());
		System.out.println("Sub total of Fluffy Bunny item is "+ fluffyBunnySubTotal);
		Thread.sleep(1000);
		String funnyCow=IWebActions.validate.getTextOf(PlanitPage.lblFunnyCowAddedItem.get());
		System.out.println(funnyCow + " item added into the cart");
		Thread.sleep(1000);
		String funnyCowSubTotal=IWebActions.validate.getTextOf(PlanitPage.lblFunnyCowSubtotal.get());
		System.out.println("Sub total of Funny Cow item is " + funnyCowSubTotal);
		Thread.sleep(1000);
		DriverFactory.getDriver().close();
		Thread.sleep(1000);
	}
	
}
