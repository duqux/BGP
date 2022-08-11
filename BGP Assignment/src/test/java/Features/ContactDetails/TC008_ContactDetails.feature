Feature:  Verification of the Save draft function

  Scenario: TC008_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I fill in main contact details
    Then I auto populate address
    And I save draft
    And I check on the main contact details
    And I check auto populate address
    Then I click on logout button



