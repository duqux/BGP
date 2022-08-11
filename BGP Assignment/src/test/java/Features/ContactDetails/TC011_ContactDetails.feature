Feature: Verification of the Text Box validations in Main Contact Person

  Scenario: TC011_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I fill in incorrect person details
    Then I click on logout button

