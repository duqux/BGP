Feature: Verification of the Text Box validations in Letter Of Offer Addressee

  Scenario: TC015_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I fill in incorrect LOOA details
    Then I click on logout button

