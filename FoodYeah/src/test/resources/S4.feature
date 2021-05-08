Feature: S04
  Scenario: S04_01
    Given a user4
    When filters the dishes
    Then the system shows the dishes that fit the filter
  Scenario: S04_02
    Given a user4
    When searches a dish
    Then the system shows the message not found
  Scenario: S04_03
    Given a user4
    When filters the dishes
    Then the system does not show any dish