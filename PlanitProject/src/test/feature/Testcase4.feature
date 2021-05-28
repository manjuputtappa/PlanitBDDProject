@TEST
Feature: Validate items are in the cart
@TEST4
Scenario: Verification items are added in the cart menu
Given launch the planit application
When click on home page and go to shop page
And click buy button "2" times on "FunnyCow"
And click buy button "1" time on "FluffyBunny"
And click the cart menu		
Then verify the items are added in the cart