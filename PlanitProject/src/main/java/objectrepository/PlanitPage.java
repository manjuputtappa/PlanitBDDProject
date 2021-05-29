package objectrepository;

import webinteractivies.Page;

public enum PlanitPage {
	lnkJupiterToys(Page.findingBy.xpath,"//h1[contains(text(),'Jupiter Toys')]"),
	lnkContactPage(Page.findingBy.xpath,"//a[text()='Contact']"),
	btnSubmit(Page.findingBy.xpath,"//a[text()='Submit']"),
	txtForeName(Page.findingBy.xpath,"//span[@id='forename-err']"),
	txtEmailID(Page.findingBy.xpath,"//span[@id='email-err']"),
	txtMessage(Page.findingBy.xpath,"//span[@id='message-err']"),
	inpForeName(Page.findingBy.xpath,"//input[@id='forename']"),
	inpSurname(Page.findingBy.xpath,"//input[@id='surname']"),
	inpEmail(Page.findingBy.xpath,"//input[@id='email']"),
	inpTelephone(Page.findingBy.xpath,"//input[@id='telephone']"),
	inpMessage(Page.findingBy.xpath,"//textarea[@id='message']"),
	txtSuccessful(Page.findingBy.xpath,"//div[@class='alert alert-success']"),
	txtIvalidMessage(Page.findingBy.xpath,"//span[@id='email-err']"),
	btnShopping(Page.findingBy.xpath,"//a[@class='btn btn-success btn-large']"),
	btnFunnyCow(Page.findingBy.xpath,"(//a[@class='btn btn-success'])[6]"),
	btnFluffyBunny(Page.findingBy.xpath,"(//a[@class='btn btn-success'])[4]"),
	btnCart(Page.findingBy.xpath,"//a[@href='#/cart']"),
	lblFunnyCow(Page.findingBy.xpath,"(//h4[@class='product-title ng-binding'])[6]"),
	lblFluffyBunny(Page.findingBy.xpath,"(//h4[@class='product-title ng-binding'])[4]"),
	lblFunnyCowAddedItem(Page.findingBy.xpath,"(//td[@class='ng-binding'])[4]"),
	lblFluffyBunnyAddedItem(Page.findingBy.xpath,"(//td[@class='ng-binding'])[1]"),
	lblFunnyCowSubtotal(Page.findingBy.xpath,"(//td[@class='ng-binding'])[6]"),
	lblFluffyBunnySubtotal(Page.findingBy.xpath,"(//td[@class='ng-binding'])[3]");
	
	private String value= "";
	PlanitPage(Page.findingBy byMethod, String byValue){
		value = byMethod.toString() + ";" + byValue + ";" + getDeclaringClass().toString().replace("class", "") + "." + name();
	}
	
	public String get() {
		return value;
	}
}
