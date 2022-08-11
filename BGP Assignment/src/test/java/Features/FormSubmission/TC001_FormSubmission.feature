Feature:  Verify that Applicant will be redirected to a read-only summary page

  Scenario: TC001_FormSubmission
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    Then I click on "Yes" for all radio buttons
    And I click on next button
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
    Then I check on review page
    Then I submit for review
    Then I click on logout button