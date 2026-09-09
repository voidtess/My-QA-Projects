import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class AutomationUtils {
    // This is a utility class for common test operations, starting a WebDriver, clicking links, waiting for elements
    public static WebDriver start(String relativeHtmlPath) {
        WebDriver driver = new ChromeDriver();
        File site = new File(relativeHtmlPath);
        driver.get(site.toURI().toString());
        driver.manage().window().maximize();
        return driver;
    }

    public static void clickLinkByText(WebDriver driver, String linkText) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
    }
}



