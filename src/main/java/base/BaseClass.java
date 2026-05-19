
package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

public class BaseClass {

    public WebDriver driver;
    public WebDriverWait wait;

    // ✅ FIX: No parameter
    public void setup() {

        driver = new ChromeDriver();  // simple (you already used this)

        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void tearDown() {
        driver.quit();
    }
}