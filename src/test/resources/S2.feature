Feature: S02
  Scenario: S02_01
    Given a user2
    When loads the dishes a la carte
    Then the system shows the description of each dish
  Scenario: S02_02
    Given a user2
    When loads the dishes a la carte
    Then the system shows the description of the filtered dish
  Scenario: S02_03
    Given a user2
    When loads the dishes a la carte
    Then the system shows the description of each product
  Scenario: S02_04
    Given a user2
    When loads the dishes a la carte
    Then the system shows the product but without image