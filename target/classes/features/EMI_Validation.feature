Feature: EMI Validation

Scenario Outline: Invalid Loan Validation

  Given user is on EMI page
  When user enters loan "<loan>"
  And user clicks calculate
  Then alert should be displayed

Examples:
| loan       |
| 0          |
| 9999       |
| -1         |
| 100000000  |
|            |