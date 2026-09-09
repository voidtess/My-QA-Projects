import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class Dropdown {

    private WebDriver driver;


    @BeforeMethod
    public void setup() {
        driver = AutomationUtils.start("src/test/resources/AutoProjectFinal/index.html");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void dropDown() {
        driver.findElement(By.linkText("Dropdowns")).click();

        Select selectCountry = new Select(driver.findElement(By.id("country-select")));
        selectCountry.selectByIndex(1); //select US because it goes after placeholder(0)
        selectCountry.selectByValue("ca");
        selectCountry.selectByVisibleText("Germany");
        List<WebElement> optionsCountry = selectCountry.getOptions();
        for (int index = 0; index < optionsCountry.size(); index++) {
            System.out.println("Options " + index + ": " + optionsCountry.get(index).getText());
        }

        Select selectContinent = new Select(driver.findElement(By.cssSelector("#continent-select")));
        selectContinent.selectByIndex(1);
        Select selectCity = new Select(driver.findElement(By.cssSelector("#city-select")));
        selectCity.selectByValue("cairo");

        Select selectSkills = new Select(driver.findElement(By.cssSelector("[name=skills]")));
//        List<WebElement> optionsSkills = selectSkills.getOptions();
//      for (int index = 0; index < optionsSkills.size(); index++) { //used for looking at options for selectByIndex
//            System.out.println("Options " + index + ": " + optionsSkills.get(index).getText());
//        }
        selectSkills.selectByIndex(1);
        selectSkills.selectByIndex(2);
        selectSkills.selectByIndex(3);

        Select selectPriority = new Select(driver.findElement(By.cssSelector("#priority-select")));
        selectPriority.selectByValue("high");

        WebElement customDropdown = driver.findElement(By.cssSelector("#custom-dropdown-toggle"));
        customDropdown.click();
        driver.findElement(By.cssSelector("#option-python")).click();
    }

    @Test
    public void radioButtons() {
        driver.findElement(By.partialLinkText("Checkboxes")).click();

        WebElement termsCheckbox = driver.findElement(By.cssSelector("#terms-checkbox"));
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

        driver.findElement(By.cssSelector("#select-all-btn")).click();

        driver.findElement(By.id("radio-beginner")).click();

        driver.findElement(By.xpath("//input[@name='language' and @value='java']")).click();
        driver.findElement(By.cssSelector("#test-integration")).click();
    }
}
