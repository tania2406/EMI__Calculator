Feature: EMI End-to-End Sequential Validation

Scenario: Execute full EMI validation flow with all data

  Given user is on EMI page

  # ===============================
  # LOAN INVALID CASES
  # ===============================
  When user enters loan "0"
  And user clicks calculate
  Then alert should be displayed

  When user enters loan "9999"
  And user clicks calculate
  Then alert should be displayed

  When user enters loan "-1"
  And user clicks calculate
  Then alert should be displayed

  When user enters loan "100000000"
  And user clicks calculate
  Then alert should be displayed

  When user enters loan ""
  And user clicks calculate
  Then alert should be displayed

  # ===============================
  # LOAN BOUNDARY
  # ===============================
  When user enters loan "10000"
  And user clicks calculate

  When user enters loan "10000000"
  And user clicks calculate

  # ===============================
  # VALID LOAN
  # ===============================
  When user enters loan "500000"
  And user clicks calculate

  # ===============================
  # INTEREST INVALID CASES
  # ===============================
  When user enters interest "0.5"
  And user clicks calculate
  Then alert should be displayed

  When user enters interest "30"
  And user clicks calculate
  Then alert should be displayed

  When user enters interest "-5"
  And user clicks calculate
  Then alert should be displayed

  When user enters interest ""
  And user clicks calculate
  Then alert should be displayed

  # ===============================
  # INTEREST BOUNDARY
  # ===============================
  When user enters interest "1"
  And user clicks calculate

  When user enters interest "25"
  And user clicks calculate

  # ===============================
  # VALID INTEREST
  # ===============================
  When user enters interest "10.5"
  And user clicks calculate

  # ===============================
  # TENURE INVALID CASES
  # ===============================
  When user enters tenure "0"
  And user clicks calculate
  Then alert should be displayed

  When user enters tenure "361"
  And user clicks calculate
  Then alert should be displayed

  When user enters tenure "-10"
  And user clicks calculate
  Then alert should be displayed

  When user enters tenure ""
  And user clicks calculate
  Then alert should be displayed

  # ===============================
  # TENURE BOUNDARY
  # ===============================
  When user enters tenure "1"
  And user clicks calculate

  When user enters tenure "360"
  And user clicks calculate

  # ===============================
  # VALID TENURE
  # ===============================
  When user enters tenure "60"
  And user clicks calculate

  # ===============================
  # LOAN CATEGORY CHECK
  # ===============================
  When user enters loan "400000"
  And user clicks calculate

  When user enters loan "1000000"
  And user clicks calculate

  When user enters loan "3000000"
  And user clicks calculate

When user enters loan "10000000"
  And user clicks calculate

  # ===============================
  # UI VALIDATION
  # ===============================
  Then charts should be visible
  And table should be displayed

  # ===============================
  # PDF INVALID
  # ===============================
  When user enters loan "9999"
  And user clicks PDF
  Then alert should be displayed

  # ===============================
  # FINAL VALID FLOW
  # ===============================
  When user enters loan "500000"
  And user enters interest "10.5"
  And user enters tenure "60"
  And user clicks calculate

  Then EMI should be displayed

  When user clicks PDF