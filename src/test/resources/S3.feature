Feature: S03
  Scenario: S03_01
    Given a user3
    When loads the order details
    Then the system substract the stock of each dish
  Scenario: S03_02
    Given a user3
    When adds dish to cart
    Then the system shows the dishes on the order
  Scenario: S03_03
    Given a user3
    When loads the order details
    Then the system substract money from the customer's card