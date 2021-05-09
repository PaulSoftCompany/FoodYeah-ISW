Feature: S12
  Scenario: S12_1
    Given a user12
    When he loads the dishes a la carte
    Then system refreshes the weekly menu
  Scenario: S12_2
    Given a user12
    When he wants to see the dishes a la carte of another day
    Then the system shows the dishes a la carte of that day
  Scenario: S12_3
    Given a user12
    When he wants to see the dishes a la carte of the week, and there is no dishes by a system error
    Then the system should show a message saying There are no dishes by the moment, please try again later