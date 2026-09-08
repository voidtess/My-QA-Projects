import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tables {

    @Test
    public void tableTest() {
        WebDriver driver = new FirefoxDriver();
        File website = new File("src/test/resources/AutoProjectFinal/index.html");
        driver.get(website.toURI().toString());
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Tables")).click();

        //Static Table
        List<WebElement> rows = driver.findElements(By.cssSelector("#static-table tbody tr"));
        System.out.println("Number of rows: " + rows.size());

        List<WebElement> columns = driver.findElements(By.cssSelector("#static-table thead th"));
        System.out.println("Number of columns: " + columns.size());

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            String studentName = cells.get(1).getText();
            System.out.println("Student Name: " + studentName);

            if (studentName.equals("David Chen")){
                String role = cells.get(3).getText();
                System.out.println("Role of David Chen: " + role);
                break;
            }
        }

        //Searchable Table
        WebElement score = driver.findElement(By.cssSelector("#searchable-table th[data-col='3']"));
        score.click();

        List<WebElement> scoreCells = driver.findElements(By.cssSelector("#searchable-table tbody tr td:nth-child(4)")); // Get all the score cells in the 4th column
        List<Integer> scores = new ArrayList<>(); // Create a list to store the scores
        for (WebElement cell : scoreCells) {
            scores.add(Integer.parseInt(cell.getText())); //
        }

        boolean isSorted = true; // Check if the scores are sorted in ascending order
        for (int i = 0; i < scores.size() - 1; i++) {
            if (scores.get(i) > scores.get(i + 1)) {
                isSorted = false;
                break;
            }
        }

        if (isSorted) {
            System.out.println("Sorting is working correctly.");
        } else {
            System.out.println("Sorting is not working correctly.");
        }

        // Complex Table (colspan & rowspan)
        // Locating Q2 Engineering Fail using row id + cell index, gets 3rd cell ('t'able'd'ata) in row with id "complex-row-q2"
        WebElement q2EngFail = driver.findElement(By.xpath("//tr[@id='complex-row-q2']/td[3]"));
        System.out.println("Q2 Engineering Fail: " + q2EngFail.getText());

        // Locating Q3 Total using following-sibling::td[last()], which gets the last cell in the row with Q3
        WebElement q3Total = driver.findElement(By.xpath("//td[@class='cell-quarter' and text()='Q3']/following-sibling::td[last()]"));
        System.out.println("Q3 Total: " + q3Total.getText());

        // Reading header colspan attribute (shows how many columns Engineering spans)
        WebElement engHeader = driver.findElement(By.id("th-eng"));
        String engText = engHeader.getText();
        String colspanAttr = engHeader.getAttribute("colspan");
        System.out.println("Engineering header: " + engText + " spans " + colspanAttr + " columns.");

        driver.quit();

    }
}
