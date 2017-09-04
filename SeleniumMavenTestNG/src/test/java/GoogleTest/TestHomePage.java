package GoogleTest;

/**
 * Created by VadymStavskyi on 9/4/2017.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestHomePage {
    private WebDriver driver;

    @BeforeClass
    private void setUp() {
        driver = new ChromeDriver();
    }

    @AfterClass
    private void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void openGooglePage() {
        driver.get("https://google.com");
    }

    @Test
    public void testSearch() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleIs("Google"));

        WebElement searchField = driver.findElement(By.id("lst-ib"));
        searchField.click();
        searchField.sendKeys("Java Selenium", Keys.ENTER);
        assert driver.getTitle().contains("Java Selenium");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

    }

}
