@TEST
Feature: Validate errors on page with invalid data
@TEST3
Scenario: Verification error with invalid data
Given launch the planit application
When click on contact page 
And enter invalid data
And click on submit button			
Then validate errors for invalid data