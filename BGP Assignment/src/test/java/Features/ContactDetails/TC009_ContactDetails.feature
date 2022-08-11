Feature:  Verification of the Save draft function

  Scenario: TC009_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I fill in letter of addressee
    And I save draft
    Then I check on the letter of offer populated fields
    Then I click on logout button

