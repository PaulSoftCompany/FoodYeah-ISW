Feature: Beer cans

  Scenario Outline: Counting beer cans
    Given I have <opening balance> beer cans
    When I have drunk <processed> beer cans
    When I go to my fridge
    Then I should <in stock> beer cans
    Examples:
      | opening balance | processed | in stock |
      | 20              | 2         | 18       |
      | 30              | 10        | 20       |




