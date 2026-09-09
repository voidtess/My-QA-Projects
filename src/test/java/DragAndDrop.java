import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DragAndDrop {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;
    //decided to make a helper methods to find elements by id, class, or name to make the code cleaner and more readable
    //in example #id, .class, [name='name'], separated by comma because its a OR operator in css selector
    private WebElement findElement(String identifier) {
        String cssSelector = "#" + identifier + ", ." + identifier + ", [name='" + identifier + "']";
        return driver.findElement(By.cssSelector(cssSelector));
    }
    private WebElement result(String identifier) {
        String cssSelector = "#" + identifier + ", ." + identifier + ", [name='" + identifier + "']";
        return driver.findElement(By.cssSelector(cssSelector));
    }

    @BeforeMethod
    public void setup() {
        driver = AutomationUtils.start("src/test/resources/AutoProjectFinal/index.html");
        AutomationUtils.clickLinkByText(driver, "Drag & Drop");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test //this test took a while to figure out, because drag and drop for some reason doesn't work on FireFox, but works on Chrome, would've reported
    public void simpleDragAndDrop() throws InterruptedException {
//      WebElement source1 = findElement("draggable-item"); commented out to use a list of boxes instead of a singe box
        List<WebElement> boxes = driver.findElements(By.cssSelector("#simple-source-pool .draggable-item")); //used to get all boxes at first
        WebElement target1 = findElement("drop-target-1");
//      action.dragAndDrop(boxes, target1).perform(); commented out to use a loop to drag and drop all boxes
        while (boxes.isEmpty()) {
            action.dragAndDrop(boxes.getFirst(), target1).perform();
            Thread.sleep(1000);
            boxes = driver.findElements(By.cssSelector("#simple-source-pool .draggable-item")); //used to update the list of boxes after DnD loop
        }
//      action.clickAndHold(source1).moveToElement(target1, 10, 10).release().perform(); more detailed way to drag and drop
        System.out.println(result("simple-dnd-result").getText());
    }

    @Test
    public void kanbanBoards() throws InterruptedException {
        List<WebElement> toDoCards = driver.findElements(By.cssSelector("#kanban-todo .kanban-card"));
        WebElement inProgressColumn = findElement("kanban-inprogress");
        while (toDoCards.isEmpty()) {
            action.dragAndDrop(toDoCards.getFirst(), inProgressColumn).perform();
            Thread.sleep(400);
            toDoCards = driver.findElements(By.cssSelector("#kanban-todo .kanban-card"));
        }

        List<WebElement> inProgressCards = driver.findElements(By.cssSelector("#kanban-inprogress .kanban-card"));
        WebElement doneColumn = findElement("kanban-done");
        while (inProgressCards.isEmpty()) {
            action.dragAndDrop(inProgressCards.getFirst(), doneColumn).perform();
            Thread.sleep(400);
            inProgressCards = driver.findElements(By.cssSelector("#kanban-inprogress .kanban-card"));
        }
        System.out.println(result("kanban-result").getText());
    }

    @Test
    public void sortCategories() throws InterruptedException {
        String[][] categoryMap = { // creates a String categoryMap, in which there are values in pairs.
                {"chip-selenium", "bucket-automation"},
                {"chip-pytest", "bucket-automation"},
                {"chip-playwright", "bucket-automation"},
                {"chip-react", "bucket-frontend"},
                {"chip-vue", "bucket-frontend"},
                {"chip-nextjs", "bucket-frontend"},
                {"chip-django", "bucket-backend"},
                {"chip-fastapi", "bucket-backend"}
        };
        for (String[] category : categoryMap) { // finds category in categoryMap, using values 0 or 1, where 0 is the first and 1 is the second.
            WebElement chip = driver.findElement(By.id(category[0]));
            WebElement bucket = driver.findElement(By.id(category[1]));
            action.dragAndDrop(chip, bucket).perform();
            Thread.sleep(400);
            System.out.println(result("bucket-result").getText());
        }
    }
}
