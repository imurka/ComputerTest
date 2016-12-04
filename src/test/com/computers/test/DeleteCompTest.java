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
public class DeleteCompTest {
    WebDriver driver;
    UpdateCompSteps updateCompSteps;
    SearchPageSteps searchPageSteps;

    @DataProvider(name = "deleteCompByName")
    public static Object[][] query() {
        return new Object[][]{{"FirstTest"},
                {"Te345st"}, {"HD.89/00"}, {"718473894782"}, {"New Name"} };
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

    @Test(dataProvider = "deleteCompByName")
    public void deleteCompTest(String name) {
        updateCompSteps = searchPageSteps.goToUpdateCompPage(name);
        Assert.assertTrue(updateCompSteps.verifyPageTitle());
        searchPageSteps = updateCompSteps.deleteComputer();
        Assert.assertTrue(searchPageSteps.verifyMessageCompIsDeleted());
    }


}
