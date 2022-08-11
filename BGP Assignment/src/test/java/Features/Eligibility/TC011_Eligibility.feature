Feature: Verification of the Save draft function

  Scenario: TC011_Eligibility
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    Then I click on "No" for all radio buttons
    And I save draft
    Then I check for saved draft for "No" radio button
