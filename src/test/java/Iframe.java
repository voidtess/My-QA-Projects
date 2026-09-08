import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Iframe {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = TestUtils.start("src/test/resources/AutoProjectFinal/index.html");
        TestUtils.clickLinkByText(driver, "iFrame");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void Iframe() {
        driver.switchTo().frame("practice-iframe");
        driver.findElement(By.id("iframe-username")).sendKeys("Tess");
        driver.findElement(By.id("iframe-password")).sendKeys("123");
        driver.findElement(By.id("iframe-email")).sendKeys("tess@example.com");
        driver.findElement(By.id("iframe-submit-btn")).click();
        System.out.println(driver.findElement(By.id("iframe-result")).getText());
        driver.findElement(By.id("iframe-link")).click();
        System.out.println(driver.findElement(By.id("iframe-result")).getText());
        driver.switchTo().defaultContent();
    }

    @Test
    public void multipleIframe() {
        //in this test is also the last part of the page that is outside the iframes, so we can interact with it as well
        System.out.println(driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame("iframe-one");
        driver.findElement(By.id("iframe-username")).sendKeys("Tess1");
        driver.findElement(By.id("iframe-submit-btn")).click();
        System.out.println(driver.findElement(By.id("iframe-result")).getText());
        driver.switchTo().defaultContent();
        driver.findElement(By.id("outside-input")).sendKeys("I am outside!");
        driver.switchTo().frame("iframe-two");
        driver.findElement(By.id("iframe-username")).sendKeys("Tess2");
        driver.findElement(By.id("iframe-submit-btn")).click();
        System.out.println(driver.findElement(By.id("iframe-result")).getText());
        driver.switchTo().defaultContent();
        driver.findElement(By.id("outside-btn")).click();
        System.out.println(driver.findElement(By.id("outside-result")).getText());
    }
}

