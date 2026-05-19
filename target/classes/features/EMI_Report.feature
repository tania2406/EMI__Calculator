Feature: EMI PDF Report

Scenario: Valid PDF Download

  Given user is on EMI page
  When user enters loan "500000"
  And user enters interest "10.5"
  And user enters tenure "60"
  And user clicks calculate
  And user clicks PDF
  Then EMI should be displayed


Scenario: Invalid PDF Download

  Given user is on EMI page
  When user enters loan "9999"
  And user clicks PDF
  Then alert should be displayed