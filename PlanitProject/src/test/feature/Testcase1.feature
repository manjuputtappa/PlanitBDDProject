@TEST
Feature: Validate errors on page of Application 
@TEST1
Scenario: Verification of error 
Given launch the planit application
When click on contact page
And click on submit button			
Then validate errors of populate mandatory fields are gone