@report
Feature: EMI Report and UI Validation

  Scenario: Validate report generation with valid data

    Given user is on EMI page

    When user enters loan "500000"
    And user enters interest "10.5"
    And user enters tenure "60"
    And user clicks calculate

    Then charts should be visible
    And amortization table should be displayed

    When user clicks download PDF
    Then PDF should be generated successfully


  Scenario: Validate PDF should NOT generate for invalid data  # DEFECT CASE

    Given user is on EMI page

    When user enters loan "800"
    And user enters interest "0.5"
    And user enters tenure "367"

    When user clicks download PDF
    Then alert should be displayed