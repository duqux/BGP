Feature:  Verify that the Mailing address auto populates correctly

  Scenario: TC002_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I auto populate address
    And I check auto populate address
    Then I click on logout button
