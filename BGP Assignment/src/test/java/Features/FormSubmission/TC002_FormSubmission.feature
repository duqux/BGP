Feature:  Verify that validation error message will prompt when mandatory fields are empty

  Scenario: TC002_FormSubmission
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I fill in main contact details
    And I click on same as registered check box
    And I click on same as main person check box
    And I click on next button
    Then I fill in proposal section
    And I click on next button
    Then I fill in business impact section
    And I click on next button
    Then I fill in cost section
    And I click on next button
    Then I fill in Declare section
    And I click on consent check box
    And I click on review button
    And I check for Mandatory
    Then I click on logout button