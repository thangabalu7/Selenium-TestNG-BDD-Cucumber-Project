

Feature: BookCart application demo

 Background: 
    Given User should navigate to application
   
    And User clicks on the login link
  @reg 
  Scenario: Login should be success
    And User enter the username as "ortoni"
    And User enter the password as "pass1234"
    When User click the login button
    Then login should be success

 @smokes
  Scenario: Login should be fail 
    And User enter the username as "koushik"
    
    And User enter the passw	ord as "passkoush"
    When User click the login button
    Then login should be failed
