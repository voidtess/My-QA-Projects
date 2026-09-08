import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class DynamicElements {
    //explicit waits, visibility checks, elements that change.

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = TestUtils.start("src/test/resources/AutoProjectFinal/index.html");
        TestUtils.clickLinkByText(driver, "Dynamic Elements");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));;
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


    @Test
    public void hiddenElement() {
        WebDriver driver = new FirefoxDriver();
        File website = new File("src/test/resources/AutoProjectFinal/index.html");
        driver.get(website.toURI().toString());
        driver.findElement(By.linkText("Dynamic Elements")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // wait for up to 10 seconds for the element to be visible
        //this section is reduntant because there is TestUtils class and beforeMethod and afterMethod, but I wanted to show how i improved over time and how I learned to use the TestUtils class to make my code more DRY and efficient, so I left it in for reference

        By boxLocator = By.id("dynamic-box");

        // Show Element
        driver.findElement(By.id("show-element-btn")).click();
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(boxLocator));
        boolean isShown = box.isDisplayed();
        if (isShown) {
            System.out.println("Element is shown when clicking the Show button");
        }

        // Hide Element
        driver.findElement(By.id("hide-element-btn")).click();
        boolean isHidden = wait.until(ExpectedConditions.invisibilityOfElementLocated(boxLocator));
        if (isHidden) {
            System.out.println("Element is hidden when clicking the Hide button");
        }

        // Toggle Element
        WebElement toggleButton = driver.findElement(By.id("toggle-element-btn"));

        // first toggle
        boolean currentlyVisible = driver.findElements(boxLocator).size() > 0 && driver.findElement(boxLocator).isDisplayed();
        // size() > 0 shows that the element exists in the DOM, "&&" returns true if both conditions are true, false otherwise
        toggleButton.click();
        if (currentlyVisible) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(boxLocator));
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(boxLocator));
        }
        boolean nowVisible = driver.findElements(boxLocator).size() > 0 && driver.findElement(boxLocator).isDisplayed();
        System.out.println("Element is " + (nowVisible ? "shown" : "hidden") + " after first toggle");

        // second toggle
        currentlyVisible = nowVisible;
        toggleButton.click();
        if (currentlyVisible) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(boxLocator));
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(boxLocator));
        }
        nowVisible = driver.findElements(boxLocator).size() > 0 && driver.findElement(boxLocator).isDisplayed();
        System.out.println("Element is " + (nowVisible ? "shown" : "hidden") + " after second toggle");
        // nowVisible ? is a ternary operator that returns "shown" if nowVisible is true, and "hidden" if nowVisible is false
        // there are safer ways to check if an element is visible, but this is a simple way to do it just for experience

    }

    @Test
    public void delayedElement() throws InterruptedException {
        By delayedBoxLocator = By.id("delayed-element");

        driver.findElement(By.id("load-delayed-btn")).click();
        WebElement delayedBox = wait.until(ExpectedConditions.visibilityOfElementLocated(delayedBoxLocator));
        boolean isShown = delayedBox.isDisplayed();
        if (isShown) {
            System.out.println("Delayed element is shown after clicking the Load Delayed Element button");
            System.out.println("Text of the delayed element: " + delayedBox.getText()); // get the text of the delayed element
        }
    }

    @Test
    public void progressBar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By progressBarLocator = By.id("progress-bar");

        driver.findElement(By.id("start-progress-btn")).click();
        driver.findElement(progressBarLocator).getAttribute("aria-valuenow"); // get the current value of the progress bar
        wait.until(d -> d.findElement(progressBarLocator).getAttribute("aria-valuenow").equals("100")); // wait until the progress bar reaches 100%%
        System.out.println("The result is: " + driver.findElement(By.id("progress-complete-msg")).getText()); // get the text of the progress complete message
        // used d -> d.findElement instead of driver -> driver.findElement because the wait.until() method takes a function
        // that takes a WebDriver as an argument and returns a boolean, so we can use d to represent the WebDriver in the "lambda" expression
    }

    @Test
    public void interactiveCounter() {
        By counterLocator = By.id("counter-value");
        WebElement incrementBtn = driver.findElement(By.id("increment-btn"));
        WebElement decrementBtn = driver.findElement(By.id("decrement-btn"));
        WebElement resetBtn = driver.findElement(By.id("reset-counter-btn"));

        incrementBtn.click();
        wait.until(ExpectedConditions.textToBe(counterLocator, "1"));

        decrementBtn.click();
        wait.until(ExpectedConditions.textToBe(counterLocator, "0"));

        for (int i = 0; i < 2; i++) {
            incrementBtn.click();
            wait.until(ExpectedConditions.textToBe(counterLocator, String.valueOf(i + 1)));
        }

        resetBtn.click();
        wait.until(ExpectedConditions.textToBe(counterLocator, "0"));
        //or use driver.findElement(counterLocator).getAttribute("data-count"); // get the current value of the counter
    }

    @Test
    public void loadedContent() {
        By skeletonLocator = By.id("skeleton-wrapper");
        By loadedContentLocator = By.id("loaded-content");

        driver.findElement(By.id("load-content-btn")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(skeletonLocator));
        System.out.println("Loaded successfully! Here is the content: " + driver.findElement(loadedContentLocator).getText());

    }

    @Test
    public void changingText() {
        WebElement button = driver.findElement(By.id("change-text-btn"));
        By textLocator = By.id("dynamic-text-element");

        boolean isFound = wait.until(d -> {
            button.click();
            String text = d.findElement(textLocator).getText();
            return text.contains("Scheduled");
        });;
        System.out.println("Message: " + driver.findElement(textLocator).getText() + " is successfully found");
    }

    @Test
    public void toastNotification() {
        By toastLocator = By.id("toast-message");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.id("trigger-toast-btn")).click();
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
        System.out.println("Toast notification is shown: " + toast.getText());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
        System.out.println("Toast notification is hidden");
    }
}
