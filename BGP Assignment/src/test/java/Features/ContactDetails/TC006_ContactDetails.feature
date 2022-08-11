Feature:  Verify if the letter of offer addressee auto populates correctly

  Scenario: TC006_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    And I click on same as main person check box
    Then I check on addressee error message
    Then I click on logout button
