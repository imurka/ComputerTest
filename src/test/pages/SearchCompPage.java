package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;
import test.steps.CreateCompSteps;
import test.steps.UpdateCompSteps;

/**
 * Created by Ирина on 02.12.2016.
 */
public class SearchCompPage {
    WebDriver driver;
    Logger log;

    @FindBy(how = How.XPATH, using = "//*[@id='main']/h1")
    public WebElement pageTitle;

    @FindBy(how = How.ID, using = "searchbox")
    public WebElement searchBox;

    @FindBy(how = How.ID, using = "searchsubmit")
    public WebElement filterByNameBtn;

    @FindBy(how = How.ID, using = "add")
    public WebElement addNewCompBtn;

    @FindBy(how = How.XPATH, using = "//*[@id='pagination']//li/a[contains(text(),'Previous']")
    public WebElement previousBtn;

    @FindBy(how = How.XPATH, using = "//*[@id='pagination']//li/a[contains(text(),'Next')]")
    public WebElement nextBtn;

    @FindBy(how = How.XPATH, using = "//*[@class='current']/a")
    public WebElement currentStatus;
    private int value;

    public SearchCompPage(WebDriver driver) {
        this.driver = driver;
        log = Logger.getLogger(CreateCompPage.class);
        PageFactory.initElements(driver, this);
    }

    public String getSearchPageTitle() {
        return pageTitle.getText();
    }


    public CreateCompSteps goToAddCompPage() {
        addNewCompBtn.click();
        return new CreateCompSteps(driver);
    }

    public void searchByName(String computerName) {
        searchBox.sendKeys(computerName);
        filterByNameBtn.click();
    }

    public boolean isNextButtonEnabled() {
        return nextBtn.isDisplayed();
    }

    public void clickNextBtn() {
        nextBtn.click();
    }

    public String getMessageForEmtyResults() {
        return driver.findElement(By.xpath("//*[@id='main']/div[2]/em")).getText();
    }

    public String getCurrentStatusPagination() {
        return currentStatus.getText();
    }

    public void goToNextPage() {
        nextBtn.click();
    }

    public void goToPreviousPage() {
        previousBtn.click();
    }


    public String getMessage() {

        String testmes = driver.findElement(By.xpath("//*[@id='main']/div[1]")).getText();

        System.out.println(testmes);
        return testmes;

    }

    public int getTotalSearchResult() {
        String[] resultSearch = driver.findElement(By.xpath("//*[@id='main']/h1")).getText().split("\\s");
        return Integer.parseInt(resultSearch[0]);
    }

    public UpdateCompSteps goToUpdateCompPage(String name) {
        searchByName(name);
        driver.findElement(By.xpath("//*[@id='main']//tbody//td/a[contains(text(),'" + name + "')]")).click();
        return new UpdateCompSteps(driver);
    }
}
