package test.steps;

import org.openqa.selenium.WebDriver;
import test.pages.UpdateCompPage;

/**
 * Created by Ирина on 04.12.2016.
 */
public class UpdateCompSteps {
    WebDriver driver;
    UpdateCompPage updateCompPage;

    public UpdateCompSteps(WebDriver driver) {
        this.driver = driver;
        updateCompPage = new UpdateCompPage(driver);
    }

    public boolean verifyPageTitle() {
        return updateCompPage.getPageTitle().equals("Edit computer");
    }

    public SearchPageSteps updateComputer(String name, String newName, String introducedDate, String discontinuedDate, String company) {
        if (!newName.equals("")){
            updateCompPage.clearCompNameField();
            updateCompPage.setCompName(newName);
        }
        if (!introducedDate.equals("")){
            updateCompPage.clearIntroducedDate();
            updateCompPage.setIntroducedDate(introducedDate);
        }
        if (!discontinuedDate.equals("")){
            updateCompPage.clearDiscontinuedDate();
            updateCompPage.setDiscontinuedDate(discontinuedDate);
        }
        updateCompPage.selectCompany(company);
        return updateCompPage.clickUpdateComp();

    }

    public SearchPageSteps deleteComputer() {
        updateCompPage.clickDeleteComp();
        return new SearchPageSteps(driver);
    }


}
