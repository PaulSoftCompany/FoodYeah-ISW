Feature: S15
  Scenario: S15_01
    Given a user8
    When tries to make a payment with saved card
    Then the system completes the fields with the saved card's info
  Scenario: S15_02
    Given a user8
    When makes a payment with new card
    Then the system shows the message to save the card
  Scenario: S15_03
    Given a user8
    When tries to makes a payment with incomplete saved card's info
    Then the system does not show the message to save the card