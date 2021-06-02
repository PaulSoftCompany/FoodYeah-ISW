Feature: S08
  Scenario: 01
    Given a user8
    When makes a payment with new card
    Then the system shows the message to save the card
  Scenario: 02
    Given a user8
    When tries to make a payment with saved card
    Then the system completes the fields with the saved card's info
  Scenario: 03
    Given a user8
    When tries to makes a payment with incomplete saved card's info
    Then the system does not show the message to save the card