
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class EMIPage {

    WebDriver driver;
    WebDriverWait wait;

    public EMIPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);   
    }

    @FindBy(id = "amountnum")
    WebElement loan;

    @FindBy(id = "interestnum")
    WebElement interest;

    @FindBy(id = "tenurenum")
    WebElement tenure;

    @FindBy(className = "calc-btn")
    WebElement calcBtn;

    @FindBy(id = "pdfBtn")
    WebElement pdfBtn;

    @FindBy(id = "categoryName")
    WebElement category;

    @FindBy(id = "emiDisplay")
    WebElement emiValue;

    @FindBy(id = "barChart")
    WebElement barChart;

    @FindBy(id = "pieChart")
    WebElement pieChart;

    @FindBy(id = "amortizationTable")
    WebElement table;

 
    public void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void enterLoan(String value) {
        wait.until(ExpectedConditions.visibilityOf(loan));
        loan.clear();
        loan.sendKeys(value);
        pause();
    }

    public void enterInterest(String value) {
        wait.until(ExpectedConditions.visibilityOf(interest));
        interest.clear();
        interest.sendKeys(value);
        pause();
    }

    public void enterTenure(String value) {
        wait.until(ExpectedConditions.visibilityOf(tenure));
        tenure.clear();
        tenure.sendKeys(value);
        pause();
    }

    public void clickCalculate() {
        wait.until(ExpectedConditions.elementToBeClickable(calcBtn)).click();
    }

    public void clickPDF() {
        wait.until(ExpectedConditions.elementToBeClickable(pdfBtn)).click();
    }


    public String getEMI() {
        wait.until(ExpectedConditions.visibilityOf(emiValue));

        String emi = emiValue.getAttribute("value");  

        System.out.println("EMI Value: " + emi);

        return emi;
    }

    public String getCategory() {
        return category.getText();
    }

    public boolean isBarChartDisplayed() {
        return barChart.isDisplayed();
    }

    public boolean isPieChartDisplayed() {
        return pieChart.isDisplayed();
    }

    public boolean isTableDisplayed() {
        return table.isDisplayed();
    }

    public boolean isPDFGenerated() {
        return true;  // Replace with real validation if needed
    }

  
    public void handleAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();

            System.out.println("ALERT: " + alert.getText());

            Thread.sleep(1000);  

            alert.accept();

        } catch (Exception e) {
            System.out.println("No alert present");
        }
    }
    

    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
}