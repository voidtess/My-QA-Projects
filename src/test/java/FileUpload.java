import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class FileUpload {

    private WebDriver driver;
    private WebDriverWait wait;
    private String filePath1;
    private String filePath2;

    @BeforeMethod
    public void setup() {
        driver = AutomationUtils.start("src/test/resources/AutoProjectFinal/index.html");
        AutomationUtils.clickLinkByText(driver, "File Upload");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        filePath1 = new File("src/test/resources/test.png").getAbsolutePath(); // get absolute path of the test.png for compatibility and anonymity
        filePath2 = new File("src/test/resources/test2.txt").getAbsolutePath();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void uploadFile() {
        driver.findElement(By.id("simple-file-input")).sendKeys(filePath1);
        System.out.println(driver.findElement(By.cssSelector("input[type='file']")).getText());
    }

    @Test
    public void uploadFileMultiple() {
        // Upload multiple files by sending the file paths separated by newline character "\n"
        driver.findElement(By.cssSelector("input[type='file'][multiple]")).sendKeys(filePath1 + "\n" + filePath2);
        System.out.println(driver.findElement(By.id("multi-file-list")).getText());
    }

    @Test
    public void uploadFileFiltered() {
        driver.findElement(By.cssSelector("#image-file-input")).sendKeys(filePath1);
        System.out.println(driver.findElement(By.cssSelector("#image-file-result")).getText());
    }

    @Test
    public void uploadFileDragAndDrop() {
        driver.findElement(By.cssSelector("#hidden-drop-input")).sendKeys(filePath1);
        System.out.println(driver.findElement(By.cssSelector("#drop-file-list")).getText());
    }

    @Test
    public void uploadFileWithSubmit() {
        driver.findElement(By.cssSelector("#uploader-name")).sendKeys("Tess");
        driver.findElement(By.cssSelector("#form-file-input")).sendKeys(filePath1);
        System.out.println(driver.findElement(By.cssSelector("#upload-form-result")).getText());
        driver.findElement(By.cssSelector("#file-description")).sendKeys("It's a test file. Nothing more to say.");
        driver.findElement(By.cssSelector("#upload-submit-btn")).click();
        System.out.println(driver.findElement(By.cssSelector("#upload-form-result")).getText());
    }
}
