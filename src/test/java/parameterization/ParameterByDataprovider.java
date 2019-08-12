package parameterization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ParameterByDataprovider {
    String driverPath = "C:\\Users\\mihovm.MASCORP\\Downloads\\selenium-java-3.141.59\\geckodriver.exe";
    WebDriver driver;

    @BeforeTest
    public void setup() {
        //Create firefox driver object
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://google.com");
    }

    /**
     * Test case to verify google search box
     *
     * @param author
     * @param searchKey
     * @throws InterruptedException
     */

    @Test(dataProvider = "SearchProvider")
    public void testMethod(String author, String searchKey) throws InterruptedException {

        WebElement searchText = driver.findElement(By.name("q"));

        //Put the search value in google searchbox
        searchText.sendKeys(searchKey);

        System.out.println("Welcome ->" + author + " Your search key is->" + searchKey);
        Thread.sleep(3000);

        String testValue = searchText.getAttribute("value");
        System.out.println(testValue + "::::" + searchKey);

        searchText.clear();
        //Verify if the value in google search box is correct
        Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
    }

    /**
     * @return Object[][] where first column contains 'author'
     * and second column contains 'searchKey'
     */

    @DataProvider (name = "SearchProvider")
    public Object[][] getDataFromDataprovider() {
        return new Object[][] {
                {"Misho", "Bulgaria"},
                {"Gogo", "USA"},
                {"Peter", "France"}
        };
    }
}
