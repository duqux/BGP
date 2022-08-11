Feature: Verification of error message and redirect url - Q1

  Scenario: TC009_Eligibility
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    Then I click on "No" radio button for "new"
    And I check for error message
