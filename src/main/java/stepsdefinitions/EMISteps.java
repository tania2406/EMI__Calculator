package stepsdefinitions;


import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EMIPage;
import utils.ExcelReader;

public class EMISteps {

    EMIPage page;

    @Given("user is on EMI page")
    public void user_is_on_page() {

        // ✅ Use Hooks base
        page = new EMIPage(Hooks.base.driver, Hooks.base.wait);
    }

    @When("user enters loan {string}")
    public void enterLoan(String loan) {
        page.enterLoan(loan);
    }

    @When("user enters interest {string}")
    public void enterInterest(String interest) {
        page.enterInterest(interest);
    }

    @When("user enters tenure {string}")
    public void enterTenure(String tenure) {
        page.enterTenure(tenure);
    }

    @When("user clicks calculate")
    public void clickCalc() {
        page.clickCalculate();
    }

    @Then("alert should be displayed")
    public void alertDisplayed() throws Exception {
        page.handleAlert();
    }

    @Then("EMI should be displayed")
    public void verifyEMI() {
        Assert.assertFalse(page.getEMI().equals("0"));
    }

    @When("user clicks PDF")
    public void clickPDF() {
        page.clickPDF();
    }

    @Then("loan category should be displayed")
    public void verifyCategory() {
        Assert.assertTrue(page.getCategory().length() > 0);
    }

    @Then("charts should be visible")
    public void chartsVisible() {
        Assert.assertTrue(page.isBarChartDisplayed());
        Assert.assertTrue(page.isPieChartDisplayed());
    }

    @Then("table should be displayed")
    public void tableDisplayed() {
        Assert.assertTrue(page.isTableDisplayed());
    }




//@When("user validates data from Excel")
//public void validateFromExcel() throws Exception {
//
//    List<String[]> data = ExcelReader.getData();
//
//    for (String[] row : data) {
//
//        String loan = row[0];
//        String interest = row[1];
//        String tenure = row[2];
//
//        System.out.println("\n--- New Test Case ---");
//
//        // ✅ Step 1: Enter Loan only
//        page.enterLoan(loan);
//        page.clickCalculate();
//
//        try {
//            page.handleAlert();
//            System.out.println("Loan Invalid ❌");
//
//            Hooks.base.driver.navigate().refresh();
//            continue;  // stop here if loan invalid
//        } catch (Exception e) {
//            System.out.println("Loan Valid ✅");
//        }
//
//        // ✅ Step 2: Enter Interest
//        page.enterInterest(interest);
//        page.clickCalculate();
//
//        try {
//            page.handleAlert();
//            System.out.println("Interest Invalid ❌");
//
//            Hooks.base.driver.navigate().refresh();
//            continue;
//        } catch (Exception e) {
//            System.out.println("Interest Valid ✅");
//        }
//
//        // ✅ Step 3: Enter Tenure
//        page.enterTenure(tenure);
//        page.clickCalculate();
//
//        try {
//            page.handleAlert();
//            System.out.println("Tenure Invalid ❌");
//        } catch (Exception e) {
//            System.out.println("All Inputs Valid ✅");
//        }
//
//        // ✅ Reset page
//        Hooks.base.driver.navigate().refresh();
//        Thread.sleep(1000);
//    }
//}

}


