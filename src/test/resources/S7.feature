Feature: S07
  Scenario: S07_01
    Given a user7
    When presses on see details
    Then the system shows the order details
  Scenario: S07_02
    Given a user7
    When loads the orders history
    Then the system shows the selected order details
  Scenario: S07_03
    Given a user7
    When loads the orders history
    Then the system shows the user's orders history