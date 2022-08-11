Feature:  Verify if the letter of offer addressee auto populates correctly

  Scenario: TC005_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I fill in main contact details
    And I click on same as main person check box
    Then I check on the letter of offer populated fields
