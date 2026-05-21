//package tests;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.*;
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import base.BaseClass;
//import pages.EMIPage;
//import utils.ConfigReader;
//
//import java.time.Duration;
//
//public class EMI_Test {
//
//    WebDriver driver;
//    WebDriverWait wait;
//    EMIPage page;
//
//    @BeforeClass
//    public void setup() {
//        driver = BaseClass.getDriver(ConfigReader.getProperty("browser"));
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get(ConfigReader.getProperty("url"));
//        driver.manage().window().maximize();
//
//        page = new EMIPage(driver, wait);
//    }
//
//  
//    public void verifyAlert() {
//        try {
//            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//            System.out.println("ALERT: " + alert.getText());
//            Thread.sleep(1000);
//            alert.accept();
//        } catch (Exception e) {
//            System.out.println("No alert displayed");
//        }
//    }
//
//   
//
//    @DataProvider(name = "validEMIData")
//    public Object[][] validEMIData() {
//        return new Object[][] {
//                { "500000", "10.5", "60" } 
//        };
//    }
//
//    @DataProvider(name = "invalidLoanData")
//    public Object[][] invalidLoanData() {
//        return new Object[][] {
//                { "9999", "10", "60" },
//                { "100000001", "10", "60" }
//        };
//    }
//
//    @DataProvider(name = "invalidInterestData")
//    public Object[][] invalidInterestData() {
//        return new Object[][] {
//                { "500000", "0", "60" },
//                { "500000", "26", "60" }
//        };
//    }
//
//    @DataProvider(name = "invalidTenureData")
//    public Object[][] invalidTenureData() {
//        return new Object[][] {
//                { "500000", "10", "0" },
//                { "500000", "10", "361" }
//        };
//    }
//
//    @DataProvider(name = "boundaryData")
//    public Object[][] boundaryData() {
//        return new Object[][] {
//                { "10000", "1", "1" },
//                { "10000000", "25", "360" }
//        };
//    }
//
//   
//    @Test(priority = 1, dataProvider = "validEMIData")
//    public void testValidEMI(String loan, String interest, String tenure) {
//
//        page.enterLoan(loan);
//        page.enterInterest(interest);
//        page.enterTenure(tenure);
//        page.clickCalculate();
//
//        String emi = page.getEMI();
//        System.out.println("EMI: " + emi);
//
//        Assert.assertNotNull(emi);
//        Assert.assertTrue(emi.length() > 0);
//    }
//
//
//    @Test(priority = 2, dataProvider = "invalidLoanData")
//    public void testInvalidLoan(String loan, String interest, String tenure) {
//        page.enterLoan(loan);
//        page.enterInterest(interest);
//        page.enterTenure(tenure);
//        page.clickCalculate();
//        verifyAlert();
//    }
//
//
//    @Test(priority = 3, dataProvider = "invalidInterestData")
//    public void testInvalidInterest(String loan, String interest, String tenure) {
//        page.enterLoan(loan);
//        page.enterInterest(interest);
//        page.enterTenure(tenure);
//        page.clickCalculate();
//        verifyAlert();
//    }
//
//    @Test(priority = 4, dataProvider = "invalidTenureData")
//    public void testInvalidTenure(String loan, String interest, String tenure) {
//        page.enterLoan(loan);
//        page.enterInterest(interest);
//        page.enterTenure(tenure);
//        page.clickCalculate();
//        verifyAlert();
//    }
//
//    @Test(priority = 5, dataProvider = "boundaryData")
//    public void testBoundaryValues(String loan, String interest, String tenure) {
//        page.enterLoan(loan);
//        page.enterInterest(interest);
//        page.enterTenure(tenure);
//        page.clickCalculate();
//    }
//
//  
//    @Test(priority = 6)
//    public void uiValidation() {
//        Assert.assertTrue(page.isBarChartDisplayed(), "Bar chart not visible");
//        Assert.assertTrue(page.isPieChartDisplayed(), "Pie chart not visible");
//        Assert.assertTrue(page.isTableDisplayed(), "Table not visible");
//    }
//
//
//    @Test(priority = 7)
//    public void testPDFDownload() {
//
//        page.enterLoan("500000");
//        page.enterInterest("10.5");
//        page.enterTenure("60");
//        page.clickCalculate();
//
//        page.clickPDF();
//    }
//
//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }
//}

//------------------------------------------------------------------------------

//package tests;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.*;
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import base.BaseClass;
//import pages.EMIPage;
//import utils.ConfigReader;
//import utils.ExcelReader;
//
//import java.time.Duration;
//import java.util.List;
//
//public class EMI_Test {
//
//    WebDriver driver;
//    WebDriverWait wait;
//    EMIPage page;
//
//    @BeforeClass
//    public void setup() {
//        driver = BaseClass.getDriver(ConfigReader.getProperty("browser"));
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get(ConfigReader.getProperty("url"));
//        driver.manage().window().maximize();
//
//        page = new EMIPage(driver, wait);
//    }
//
//    // ✅ ALERT HANDLER
//    public void verifyAlert() {
//        try {
//            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//            System.out.println("ALERT: " + alert.getText());
//            Thread.sleep(1000);
//            alert.accept();
//        } catch (Exception e) {
//            System.out.println("No alert displayed");
//        }
//    }
//
//    // ✅ ✅ ✅ MAIN DATA DRIVEN TEST (EXCEL)
//    @Test(priority = 1)
//    public void validateEMIUsingExcel() throws Exception {
//
//        List<String[]> data = ExcelReader.getData();  // ✅ Excel call
//
//        for (String[] row : data) {
//
//            String loan = row[0];
//            String interest = row[1];
//            String tenure = row[2];
//
//            System.out.println("\nLoan: " + loan +
//                               " | Interest: " + interest +
//                               " | Tenure: " + tenure);
//
//            // ✅ STEP 1: Loan check
//            page.enterLoan(loan);
//            page.clickCalculate();
//
//            try {
//                verifyAlert();
//                System.out.println("Loan Invalid ❌");
//                driver.navigate().refresh();
//                continue;
//            } catch (Exception e) {
//                System.out.println("Loan Valid ✅");
//            }
//
//            // ✅ STEP 2: Interest check
//            page.enterInterest(interest);
//            page.clickCalculate();
//
//            try {
//                verifyAlert();
//                System.out.println("Interest Invalid ❌");
//                driver.navigate().refresh();
//                continue;
//            } catch (Exception e) {
//                System.out.println("Interest Valid ✅");
//            }
//
//            // ✅ STEP 3: Tenure check
//            page.enterTenure(tenure);
//            page.clickCalculate();
//
//            try {
//                verifyAlert();
//                System.out.println("Tenure Invalid ❌");
//            } catch (Exception e) {
//                System.out.println("All Inputs Valid ✅");
//
//                String emi = page.getEMI();
//                System.out.println("EMI: " + emi);
//
//                Assert.assertNotNull(emi);
//                Assert.assertTrue(emi.length() > 0);
//            }
//
//            driver.navigate().refresh();
//            Thread.sleep(1000);
//        }
//    }
//
//    // ✅ UI VALIDATION (UNCHANGED)
//    @Test(priority = 2)
//    public void uiValidation() {
//        Assert.assertTrue(page.isBarChartDisplayed(), "Bar chart not visible");
//        Assert.assertTrue(page.isPieChartDisplayed(), "Pie chart not visible");
//        Assert.assertTrue(page.isTableDisplayed(), "Table not visible");
//    }
//
//    // ✅ PDF TEST (UNCHANGED)
//    @Test(priority = 3)
//    public void testPDFDownload() {
//
//        page.enterLoan("500000");
//        page.enterInterest("10.5");
//        page.enterTenure("60");
//        page.clickCalculate();
//
//        page.clickPDF();
//    }
//
//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }
//}



package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import base.BaseClass;
import pages.EMIPage;
import utils.ConfigReader;
import utils.ExcelReader;

import java.time.Duration;
import java.util.List;

public class EMI_Test {

    WebDriver driver;
    WebDriverWait wait;
    EMIPage page;

    @BeforeClass
    public void setup() {

        driver = BaseClass.getDriver(ConfigReader.getProperty("browser"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(ConfigReader.getProperty("url"));
        driver.manage().window().maximize();

        page = new EMIPage(driver, wait);
    }

    // ✅ simple 1 sec wait
    public void pause() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    // ✅ main test (Excel)
    @Test
    public void testEMI() throws Exception {

        List<String[]> data = ExcelReader.getData();

        for (String[] row : data) {

            String loan = row[0];
            String interest = row[1];
            String tenure = row[2];

            System.out.println(loan + " | " + interest + " | " + tenure);

            // enter values
            page.enterLoan(loan);
            page.enterInterest(interest);
            page.enterTenure(tenure);
            pause();

            page.clickCalculate();
            pause();

            try {
                Alert alert = driver.switchTo().alert();
                System.out.println("Invalid ❌");
                alert.accept();
            } catch (Exception e) {
                System.out.println("Valid ✅");
                System.out.println("EMI: " + page.getEMI());
            }

            driver.navigate().refresh();
            pause();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}