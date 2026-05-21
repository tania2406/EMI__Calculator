@functional
Feature: EMI Functional Validation

  Scenario Outline: Validate EMI calculation and boundary values

    Given user is on EMI page

    When user enters loan "<loan>"
    And user enters interest "<interest>"
    And user enters tenure "<tenure>"
    And user clicks calculate

    Then EMI should be displayed
    And EMI value should be correct

    Examples:
      | loan      | interest | tenure |
      | 500000    | 10.5     | 60     |
      | 10000     | 1        | 1      |
      | 10000000  | 25       | 360    |
