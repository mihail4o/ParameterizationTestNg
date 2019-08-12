package parameterization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ParameterDataproviderWithClassLevel {
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

    @Test (dataProvider = "SearchProvider", dataProviderClass = DataproviderClass.class)
    public void testMethod(String author, String searchKey) throws InterruptedException {

        WebElement searchText = driver.findElement(By.name("q"));
        // Put in search box field
        searchText.sendKeys(searchKey);

        System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
        Thread.sleep(3000);
        //get text from search box
        String textValue = searchText.getAttribute("value");
        System.out.println(textValue +"::::"+searchKey);
        searchText.clear();
        //verify if search box has correct value
        Assert.assertTrue(textValue.equalsIgnoreCase(searchKey));
    }

}
