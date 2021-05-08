Feature: S01
  Scenario: S01_01
    Given a user1
    When loads the weekly menu
    Then the application shows the description of each dish
  Scenario: S01_02
    Given a user1
    When loads the weekly menu
    Then the application has to show the dishes by dates
  Scenario: S01_03
    Given a user1
    When loads the weekly menu
    Then the application refresh the weekly menu
  Scenario: S01_04
    Given a user1
    When loads the weekly menu
    Then the system shows the dishes names