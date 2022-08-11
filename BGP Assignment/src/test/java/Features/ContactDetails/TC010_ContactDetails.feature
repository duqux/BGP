Feature:  Verification of the Save draft function

  Scenario: TC010_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I fill in main contact details
    And I click on same as registered check box
    And I click on same as main person check box
    And I save draft
    And I check on the main contact details
    Then I check on the populated fields
    Then I check on the letter of offer populated fields
    Then I click on logout button

