Feature: S09
  Scenario: S09_1
    Given a user9
    When he opens an account with his card
    Then the system shows a list of cards of the user
  Scenario: S09_2
    Given a user9 with no cards saved
    When he wants to see the information about his card
    Then the system shows a message showing that he has no cards saved
  Scenario: S09_3
    Given a user9
    When he see that he has no cards saved
    Then the system suggests to save a new card