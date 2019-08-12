package parameterization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class ParameterByMethodInDataprovider {
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
    public void testMethodA(String author, String searchKey) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(searchKey);

        // Show author and key
        System.out.println("Welcome " + author + " your key value is: " + searchKey);
        Thread.sleep(3000);

        // Get text from search field
        String testValue = searchText.getAttribute("value");
        System.out.println(testValue + " ::: " + searchKey);
        searchText.clear();
        // Verify it
        Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
    }

    @Test(dataProvider = "SearchProvider")
    public void testMethodB(String searchKey) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(searchKey);

        // Show author and key
        System.out.println("Welcome author! Your key value is: " + searchKey);
        Thread.sleep(3000);

        // Get text from search field
        String testValue = searchText.getAttribute("value");
        System.out.println(testValue + " ::: " + searchKey);
        searchText.clear();
        // Verify it
        Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
    }

    /**
     * Here DataProvider returning value on the basis of test method name
     *
     * @param m
     * @return
     **/

    @DataProvider(name = "SearchProvider")
    public Object[][] getDataFromDataprovider(Method m) {
        if (m.getName().equalsIgnoreCase("testMethodA")) {
            return new Object[][] {
                    {"misho", "veliko tarnovo"},
                    {"bally", "sofia"},
                    {"niko", "germany"}
            };

        } else {
            return new Object[][] {
                    {"burma"},
                    {"kokoshka"},
                    {"Baba meca"}
            };
        }
    }

}
