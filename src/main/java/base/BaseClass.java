
package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

public class BaseClass {

	public WebDriver driver;
	public WebDriverWait wait;

	public void setup() {

		String browser = ConfigReader.getProperty("browser");

		driver = getDriver(browser);

		driver.manage().window().maximize();
		driver.get(ConfigReader.getProperty("url"));

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public static WebDriver getDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome"))
			return new ChromeDriver();
		else if (browser.equalsIgnoreCase("edge"))
			return new EdgeDriver();
		else {
			System.out.println("Invalid Browser");
			return null;
		}
	}

	public void tearDown() {
		// driver.quit();
	}
}