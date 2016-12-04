package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;
import test.steps.SearchPageSteps;

import java.util.List;

/**
 * Created by Ирина on 02.12.2016.
 */
public class UpdateCompPage {
    WebDriver driver;
    Logger log;

    @FindBy(how = How.ID, using = "name")
    public WebElement compName;

    @FindBy(how = How.ID, using = "introduced")
    public WebElement introducedDate;

    @FindBy(how = How.ID, using = "discontinued")
    public WebElement discontinuedDate;

    @FindBy(how = How.XPATH, using = "//select[@id='company']")
    public WebElement companyDropdown;

    @FindBy(how = How.XPATH, using = "//input[@value='Save this computer']")
    public WebElement updateCompBtn;

    @FindBy(how = How.XPATH, using = "//input[@value='Delete this computer']")
    public WebElement deleteCompBtn;

    @FindBy(how = How.XPATH, using = "//*[@text='Cancel']")
    public WebElement cancelBtn;

    @FindBy(how = How.XPATH, using = "//*[@id='main']/h1")
    public WebElement pageTitle;

    public UpdateCompPage(WebDriver driver) {
        this.driver = driver;
        log = Logger.getLogger(UpdateCompPage.class);
        PageFactory.initElements(driver, this);
    }

    public void setCompName(String name) {
        compName.sendKeys(name);
    }

    public void setIntroducedDate(String intrDate) {
        introducedDate.sendKeys(intrDate);
    }

    public void setDiscontinuedDate(String discontDate) {
        discontinuedDate.sendKeys(discontDate);
    }

    public void selectCompany(String companyName) {
        if (!companyName.equals("")) {
            companyDropdown.click();
            List<WebElement> companyList = driver.findElements(By.xpath("//select[@id='company']/option"));
            for (WebElement option : companyList) {
                if (option.getText().equals(companyName)) {
                    option.click();
                    break;
                }
            }
        }
    }

    public SearchPageSteps clickUpdateComp() {
        updateCompBtn.submit();
        log.info("Click on Save button");
        return new SearchPageSteps(driver);
    }

    public void clickDeleteComp() {
        deleteCompBtn.submit();
        log.info("Click on Delete button");
    }


    public void cancel() {
        cancelBtn.click();
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public boolean getInlineHelpMessage() {
        List<WebElement> fieldsList = driver.findElements(By.xpath("//*[@id='main']//fieldset/div[position()<4]"));
        for (WebElement el : fieldsList) {
            if (!el.getAttribute("class").contains("error")) {
                return false;
            }
        }
        return true;
    }

    public void clearCompNameField() {
        compName.clear();
    }

    public void clearIntroducedDate() {
        introducedDate.clear();
    }

    public void clearDiscontinuedDate() {
        discontinuedDate.clear();
    }
}

