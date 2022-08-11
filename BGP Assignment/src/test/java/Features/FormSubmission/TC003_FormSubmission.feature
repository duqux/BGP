Feature:  Verify that validation error message will prompt when mandatory fields are empty

  Scenario: TC003_FormSubmission
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    Then I click on "Yes" for all radio buttons
    And I click on next button
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
    Then I check mandatory for Contact Details
    Then I click on logout button