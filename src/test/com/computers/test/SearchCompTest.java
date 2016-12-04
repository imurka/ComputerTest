package test.com.computers.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import test.steps.SearchPageSteps;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ирина on 02.12.2016.
 */
public class SearchCompTest {
    WebDriver driver;
    SearchPageSteps searchPageSteps;

    @DataProvider(name = "searchComputer")
    public static Object[][] query() {
        return new Object[][]{{"Sony Vaio P VGN-P530H/G"}, {"325"}, {"asci blue"}};
    }

    @DataProvider(name = "searchComputerMultiple")
    public static Object[][] queryMultiple() {
        return new Object[][]{{"am"}};
    }

    @DataProvider(name = "searchComputerNegative")
    public static Object[][] queryNegative() {
        return new Object[][]{{"**pp**"}, {"App*"}, {"    "}, {"01 Jan 1993"}};
    }

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:/Homework/task/ComputersTest/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://computer-database.herokuapp.com/computers");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        searchPageSteps = new SearchPageSteps(driver);
    }

    @AfterMethod
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "searchComputer")
    public void searchCompTest(String name) {
        searchPageSteps.searchCompByName(name);
        Assert.assertTrue(searchPageSteps.verifyOnePageSearchResults(name));
    }

    @Test(dataProvider = "searchComputerMultiple")
    public void searchCompMultipleTest(String name) {
        Assert.assertTrue(searchPageSteps.verifyMultiplePageSearchResults(name));
    }

    @Test(dataProvider = "searchComputerNegative")
    public void searchCompTestNegative(String name) {
        searchPageSteps.verifyMessageForEmptyResults(name);
    }


}
