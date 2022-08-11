Feature:  Verify that application cannot be submitted without clicking on 'Consent and Acknowledgment' checkbox.

  Scenario: TC004_FormSubmission
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
    And I click on review button
    Then I check mandatory for consent
    Then I click on logout button