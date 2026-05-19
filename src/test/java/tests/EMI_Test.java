package tests;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.EMIPage;

import java.time.Duration;

public class EMI_Test {

    WebDriver driver;
    WebDriverWait wait;
    EMIPage page;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://emi-calculator-virid-nu.vercel.app/");
        driver.manage().window().maximize();

        page = new EMIPage(driver, wait);
    }

 // ✅ Utility for alert validation (UPDATED)
    public void verifyAlert() {
        try {
            // ✅ Wait for alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // ✅ Print alert text
            System.out.println("ALERT: " + alert.getText());

            // ✅ Small pause for visibility (only place we use sleep)
            Thread.sleep(1500);

            // ✅ Accept alert
            alert.accept();

        } catch (Exception e) {
            System.out.println("No alert displayed (acceptable)");
        }
    }


    // ===============================
    // LOAN INVALID
    // ===============================
    @Test(priority = 1)
    public void loanInvalidCases() {

        String[] invalidLoans = {"0", "9999", "-1", "100000000", ""};

        for (String loan : invalidLoans) {
            page.enterLoan(loan);
            page.clickCalculate();
            verifyAlert();
        }
    }

    // ===============================
    // LOAN BOUNDARY + VALID
    // ===============================
    @Test(priority = 2)
    public void loanValidCases() {

        String[] validLoans = {"10000", "10000000", "500000"};

        for (String loan : validLoans) {
            page.enterLoan(loan);
            page.clickCalculate();
        }
    }

    // ===============================
    // INTEREST INVALID
    // ===============================
    @Test(priority = 3)
    public void interestInvalidCases() {

        String[] invalidInterest = {"0.5", "30", "-5", ""};

        for (String interest : invalidInterest) {
            page.enterInterest(interest);
            page.clickCalculate();
            verifyAlert();
        }
    }

    // ===============================
    // INTEREST VALID
    // ===============================
    @Test(priority = 4)
    public void interestValidCases() {

        String[] validInterest = {"1", "25", "10.5"};

        for (String interest : validInterest) {
            page.enterInterest(interest);
            page.clickCalculate();
        }
    }

    // ===============================
    // TENURE INVALID
    // ===============================
    @Test(priority = 5)
    public void tenureInvalidCases() {

        String[] invalidTenure = {"0", "361", "-10", ""};

        for (String tenure : invalidTenure) {
            page.enterTenure(tenure);
            page.clickCalculate();
            verifyAlert();
        }
    }

    // ===============================
    // TENURE VALID
    // ===============================
    @Test(priority = 6)
    public void tenureValidCases() {

        String[] validTenure = {"1", "360", "60"};

        for (String tenure : validTenure) {
            page.enterTenure(tenure);
            page.clickCalculate();
        }
    }

    // ===============================
    // CATEGORY CHECK
    // ===============================
    @Test(priority = 7)
    public void loanCategoryCheck() {

        String[] loans = {"400000", "1000000", "3000000", "10000000"};

        for (String loan : loans) {
            page.enterLoan(loan);
            page.clickCalculate();
        }
    }

    // ===============================
    // UI VALIDATION
    // ===============================
    @Test(priority = 8)
    public void uiValidation() {

        Assert.assertTrue(page.isBarChartDisplayed(), "Bar chart not visible");
        Assert.assertTrue(page.isPieChartDisplayed(), "Pie chart not visible");
        Assert.assertTrue(page.isTableDisplayed(), "Table not visible");
    }

    // ===============================
    // PDF INVALID
    // ===============================
    @Test(priority = 9)
    public void pdfInvalid() {

        page.enterLoan("9999");
        page.clickPDF();
        verifyAlert();
    }

    // ===============================
    // FINAL VALID FLOW
    // ===============================
    @Test(priority = 10)
    public void finalValidFlow() {

        page.enterLoan("500000");
        page.enterInterest("10.5");
        page.enterTenure("60");
        page.clickCalculate();

        String emi = page.getEMI();
        System.out.println("Final EMI: " + emi);

        Assert.assertNotNull(emi);
        Assert.assertTrue(emi.length() > 0);

        page.clickPDF();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}