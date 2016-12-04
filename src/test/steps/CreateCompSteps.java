package test.steps;

import org.openqa.selenium.WebDriver;
import test.pages.CreateCompPage;

/**
 * Created by Ирина on 02.12.2016.
 */
public class CreateCompSteps {
    WebDriver driver;
    CreateCompPage createCompPage;

    public CreateCompSteps(WebDriver driver) {
        this.driver = driver;
        createCompPage = new CreateCompPage(driver);
    }

    public boolean verifyPageTitle() {
        return createCompPage.getPageTitle().equals("Add a computer");
    }

    public SearchPageSteps addNewComputer(String name, String introducedDate, String discontinuedDate, String company) throws InterruptedException {
        createCompPage.setCompName(name);
        createCompPage.setIntroducedDate(introducedDate);
        createCompPage.setDiscontinuedDate(discontinuedDate);
        createCompPage.selectCompany(company);
        return createCompPage.createComp();

    }

    public boolean addNewComputerNegative(String name, String introducedDate, String discontinuedDate, String company) throws InterruptedException {
        createCompPage.setCompName(name);
        createCompPage.setIntroducedDate(introducedDate);
        createCompPage.setDiscontinuedDate(discontinuedDate);
        createCompPage.selectCompany(company);
        createCompPage.createComp();
        return verifyPageTitle();
    }

    public boolean verifyRequiredFieldMessage(String name, String introducedDate, String discontinuedDate, String company) throws InterruptedException {
        createCompPage.setCompName(name);
        createCompPage.setIntroducedDate(introducedDate);
        createCompPage.setDiscontinuedDate(discontinuedDate);
        createCompPage.clickCreateComp();
        return createCompPage.getInlineHelpMessage();
    }
}
