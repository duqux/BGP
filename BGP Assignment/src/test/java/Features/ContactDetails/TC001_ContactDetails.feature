Feature:  Verification that the 'Main Contact Person' subsection contains the right inputs

  Scenario: TC001_ContactDetails
    Given I navigate to portal
    And I login to portal
    Then I create an application form
    And I click on "Contact Details" section tab
    Then I check contact details fields
    Then I click on logout button