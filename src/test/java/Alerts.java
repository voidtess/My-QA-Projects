import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Alerts {

    @Test
    public void Alert() {
        WebDriver driver = new ChromeDriver();
        File website = new File("src/test/resources/AutoProjectFinal/index.html");
        driver.get(website.toURI().toString());
        driver.findElement(By.partialLinkText("Alerts")).click();

        // Simple Alert
        driver.findElement(By.id("trigger-alert-btn")).click();
        driver.switchTo().alert().accept();

        // Confirm Alert
        driver.findElement(By.id("trigger-confirm-btn")).click();
        driver.switchTo().alert().accept();
        System.out.println("Confirm Result: " + driver.findElement(By.id("confirm-result")).getText());
        driver.findElement(By.id("trigger-confirm-btn")).click();
        driver.switchTo().alert().dismiss();
        System.out.println("Confirm Result: " + driver.findElement(By.id("confirm-result")).getText());

        // Prompt Alert
        driver.findElement(By.id("trigger-prompt-btn")).click();
        driver.switchTo().alert().sendKeys("Tess Test, haha joke");
        driver.switchTo().alert().accept();
        System.out.println("Prompt Result: " + driver.findElement(By.id("prompt-result")).getText());

        driver.findElement(By.id("show-success-alert-btn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("Success Notification: " + wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("success-notification")))).getText());
        driver.findElement(By.id("show-error-alert-btn")).click();
        System.out.println("Error Notification: " + wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("error-notification")))).getText());
        driver.findElement(By.id("show-info-alert-btn")).click();
        System.out.println("Info Notification: " + wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("info-notification")))).getText());
        driver.findElement(By.id("hide-all-alerts-btn")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated((By.id("success-notification"))));
        wait.until(ExpectedConditions.invisibilityOfElementLocated((By.id("error-notification"))));
        wait.until(ExpectedConditions.invisibilityOfElementLocated((By.id("info-notification"))));
        System.out.println("All alerts are hidden.");

        driver.findElement(By.id("open-modal-btn")).click();
        driver.findElement(By.id("modal-input")).sendKeys("something...");
        driver.findElement(By.id("modal-confirm-btn")).click();
        System.out.println("Modal Result: " + driver.findElement(By.id("modal-result")).getText());

        driver.quit();
    }
}
