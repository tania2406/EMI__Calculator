package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class EMIPage {

    WebDriver driver;
    WebDriverWait wait;

    public EMIPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators
    By loan = By.id("amountnum");
    By interest = By.id("interestnum");
    By tenure = By.id("tenurenum");
    By calcBtn = By.className("calc-btn");
    By pdfBtn = By.id("pdfBtn");
    By category = By.id("categoryName");
    public void pause() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {}
    }
    // Actions
    public void enterLoan(String value) {
        driver.findElement(loan).clear();
        driver.findElement(loan).sendKeys(value);
        pause();
    }

    public void enterInterest(String value) {
        driver.findElement(interest).clear();
        driver.findElement(interest).sendKeys(value);
        pause();
    }

    public void enterTenure(String value) {
        driver.findElement(tenure).clear();
        driver.findElement(tenure).sendKeys(value);
        pause();
    }

    public void clickCalculate() {
        wait.until(ExpectedConditions.elementToBeClickable(calcBtn)).click();
    }

    public void clickPDF() {
        wait.until(ExpectedConditions.elementToBeClickable(pdfBtn)).click();
        pause();
    }

    public String getEMI() {
        return driver.findElement(By.id("emiDisplay")).getAttribute("value");
        
    }

    public String getCategory() {
        return driver.findElement(category).getText();
    }

    public boolean isBarChartDisplayed() {
        return driver.findElement(By.id("barChart")).isDisplayed();
    }

    public boolean isPieChartDisplayed() {
        return driver.findElement(By.id("pieChart")).isDisplayed();
    }

    public boolean isTableDisplayed() {
        return driver.findElement(By.id("amortizationTable")).isDisplayed();
    }

    public int getTableRows() {
        return driver.findElements(By.xpath("//table[@id='amortizationTable']//tr")).size();
    }

    public void handleAlert() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("ALERT: " + alert.getText());

            Thread.sleep(2000); // view alert
            alert.accept();

        } catch (Exception e) {
            // No alert
        }
    }
    public String getAlertText() {

        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        return text;
    }
}