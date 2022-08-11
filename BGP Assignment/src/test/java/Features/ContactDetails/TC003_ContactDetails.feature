Feature:  Verify that the Mailing address auto populates correctly

  Scenario: TC003_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I click on same as registered check box
    And I check on the populated fields
    Then I click on logout button
