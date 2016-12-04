package test.com.computers.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import test.steps.CreateCompSteps;
import test.steps.SearchPageSteps;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ирина on 02.12.2016.
 */
public class CreateCompTest {
    WebDriver driver;
    CreateCompSteps createCompSteps;
    SearchPageSteps searchPageSteps;

    @DataProvider(name = "newCompPositive")
    public static Object[][] query() {
        return new Object[][]{{"FirstTest", "2013-12-01", "2013-12-01", "Samsung Electronics"},
                {"Te345st", "", "2013-12-01", "Apple Inc"}, {"HD.89/00", "2013-12-01", "", "Samsung Electronics"},
                {"718473894782", "", "", ""}};
    }

    @DataProvider(name = "newCompNegative")
    public static Object[][] queryNegative() {
        return new Object[][]{{"", "2013-12-01", "2013-12-01", "Samsung Electronics"},
                {"    ", "", "2013-12-01", ""}, {"HD.89/00", "sdfssdf", "2013-12-01", "Samsung Electronics"},
                {"718473894782", "", "20131201", ""}};
    }

    @DataProvider(name = "fieldsHighlightedAsRed")
    public static Object[][] queryErrorSaving() {
        return new Object[][]{{"", "20131201", "dghg", ""}};
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

    @Test(dataProvider = "newCompPositive")
    public void addCompTest(String name, String introducedDate, String discontinuedDate, String company) throws InterruptedException {
        createCompSteps = searchPageSteps.goToAddCompPage();
        Assert.assertTrue(createCompSteps.verifyPageTitle());
        searchPageSteps = createCompSteps.addNewComputer(name, introducedDate, discontinuedDate, company);
        Assert.assertTrue(searchPageSteps.verifyMessageNewCompIsCreated(name));
    }

    @Test(dataProvider = "newCompNegative")
    public void addCompTestNegative(String name, String introducedDate, String discontinuedDate, String company) throws InterruptedException {
        createCompSteps = searchPageSteps.goToAddCompPage();
        Assert.assertTrue(createCompSteps.verifyPageTitle());
        Assert.assertTrue(createCompSteps.addNewComputerNegative(name, introducedDate, discontinuedDate, company));
    }

    @Test(dataProvider = "fieldsHighlightedAsRed")
    public void verifyRequiredFields(String name, String introducedDate, String discontinuedDate, String company) throws InterruptedException {
        createCompSteps = searchPageSteps.goToAddCompPage();
        Assert.assertTrue(createCompSteps.verifyPageTitle());
        Assert.assertTrue(createCompSteps.verifyRequiredFieldMessage(name, introducedDate, discontinuedDate, company));
    }

}
