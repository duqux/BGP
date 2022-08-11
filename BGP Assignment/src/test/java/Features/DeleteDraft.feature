Feature:  To delete drafts created

  Scenario: DeleteDraft
    Given I navigate to portal
    And I login to portal
    Then I delete drafts
    Then I click on logout button

