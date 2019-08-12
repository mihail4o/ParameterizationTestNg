package parameterization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NoParameterWithTestNGXML {
//    static {
//        System.setProperty("webdriver.gecko.driver",
//                "C:\\Users\\mihovm.MASCORP\\Downloads\\selenium-java-3.141.59\\geckodriver.exe");
////                "/Users/balivo/Downloads/selenium/course/geckodriver");
//    }

    String driverPath = "C:\\Users\\mihovm.MASCORP\\Downloads\\selenium-java-3.141.59\\geckodriver.exe";
    WebDriver driver;

@Test
public void testNoParameter() throws InterruptedException {
    String author = "Mihail";
    String searchKey = "Bulgaria";

    System.setProperty("webdriver.gecko.driver", driverPath);
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    driver.get("https://google.com");
    WebElement searchText = driver.findElement(By.name("q"));
    //Searching text in google text box
    searchText.sendKeys(searchKey);

    System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
    System.out.println("Thread will sleep now");

    Thread.sleep(3000);
    System.out.println("Value in Google Search Box = " + searchText.getAttribute("value")
            + " ::: Value given by input = " + searchKey);

    //verifying the value in google search box
    Assert.assertTrue(searchText.getAttribute("value").equalsIgnoreCase(searchKey));
}

}
