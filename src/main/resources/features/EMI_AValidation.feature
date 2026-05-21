@validation
Feature: EMI Input Validation

  Scenario Outline: Validate invalid inputs

    Given user is on EMI page

    When user enters loan "<loan>"
    And user enters interest "<interest>"
    And user enters tenure "<tenure>"
    And user clicks calculate

    Then alert should be displayed

    Examples:
      | loan        | interest | tenure |
      | 9999        | 10       | 60     |
      | 100000001   | 10       | 60     |
      | 500000      | 0        | 60     |
      | 500000      | 26       | 60     |
      | 500000      | 10       | 0      |
      | 500000      | 10       | 361    |
