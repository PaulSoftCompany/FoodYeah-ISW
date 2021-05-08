Feature: S14
  Scenario: S14_1
    Given a user14
    When he buys a menu when there is no stock
    Then the system cancel the process and show a error message
  Scenario: S14_2
    Given a user14
    When he buys a dish from dishes a la carte when there is no stock
    Then the system cancel the process and show a error message
  Scenario: S14_3
    Given a user14
    When he buys a dish when there is no stock
    Then the system cancel the process and show a error message
