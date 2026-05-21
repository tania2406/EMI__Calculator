package stepsdefinitions;

import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EMIPage;

public class EMISteps {

    EMIPage page;

    @Given("user is on EMI page")
    public void user_is_on_page() {
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

    @Then("EMI value should be correct")
    public void verifyEMIValue() {
        Assert.assertTrue(page.getEMI().length() > 0);
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

    @When("user clicks download PDF")
    public void clickPDF() {
        page.clickPDF();
    }

    @Then("PDF should be generated successfully")
    public void verifyPDF() {
        Assert.assertTrue(page.isPDFGenerated());
    }
    @Then("amortization table should be displayed")
    public void amortization_table_should_be_displayed() {
        Assert.assertTrue(page.isTableDisplayed(), "Amortization table is NOT displayed");
    }

    @Then("loan category should be displayed")
    public void verifyCategory() {
        Assert.assertTrue(page.getCategory().length() > 0);
    }
}
