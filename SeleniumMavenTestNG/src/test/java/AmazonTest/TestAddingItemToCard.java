package AmazonTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by VadymStavskyi on 9/4/2017.
 */
public class TestAddingItemToCard {
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
    public void openAmazonPage() {
        driver.get("https://amazon.com");
    }

    @Test
    public void testAddingItemToCard() {
        new Select(driver.findElement(By.id("searchDropdownBox"))).selectByVisibleText("Books");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Selenium WebDriver Java");
        driver.findElement(By.xpath("//*[@value=\"Go\"]")).click();

        WebElement firstTitleInTheList = driver.findElement(By.className("s-access-title"));
        String firstItemTitle = firstTitleInTheList.getText();
        firstTitleInTheList.click();

        assert (driver.findElement(By.id("productTitle")).getText().equals(firstItemTitle));

        driver.findElement(By.id("add-to-cart-button")).click();

        assert (driver.findElement(By.id("huc-v2-order-row-confirm-text")).getText().equals("Added to Cart"));

        driver.findElement(By.id("hlb-view-cart-announce")).click();

        assert (driver.findElement(By.className("sc-product-title")).getText().contains(firstItemTitle));

    }
}
