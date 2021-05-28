@TEST
Feature: Validate successful submission message 
@TEST2
Scenario: Verification successful submission message
Given launch the planit application
When click on contact page 
And enter all details in contact page
And click on submit button			
Then validate successful submission message on the page