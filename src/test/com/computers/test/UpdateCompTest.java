package test.com.computers.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.steps.CreateCompSteps;
import test.steps.SearchPageSteps;
import test.steps.UpdateCompSteps;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ирина on 02.12.2016.
 */
public class UpdateCompTest {
    WebDriver driver;
    UpdateCompSteps updateCompSteps;
    SearchPageSteps searchPageSteps;

    @DataProvider(name = "updateCompPositive")
    public static Object[][] query() {
        return new Object[][] {{"FirstTest", "", "2014-12-01", "2014-12-01", "Samsung Electronics"},
                {"Te345st", "", "", "2016-12-01", "Apple Inc"}, {"HD.89/00", "New Name", "2013-12-01", "", ""},
                {"718473894782", "", "", "2016-12-01", "Samsung Electronics"}};
    }

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:/Homework/task/ComputersTest/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://computer-database.herokuapp.com/computers");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        searchPageSteps = new SearchPageSteps(driver);
    }

    @AfterClass
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "updateCompPositive")
    public void updateCompTest(String name, String newName, String introducedDate, String discontinuedDate, String company) throws InterruptedException {
        updateCompSteps = searchPageSteps.goToUpdateCompPage(name);
        Assert.assertTrue(updateCompSteps.verifyPageTitle());
        searchPageSteps = updateCompSteps.updateComputer(name, newName, introducedDate, discontinuedDate, company);
        if (!newName.equals("")) {
            Assert.assertTrue(searchPageSteps.verifyMessageCompIsUpdated(newName));
        }else{Assert.assertTrue(searchPageSteps.verifyMessageCompIsUpdated(name));}
    }



}
