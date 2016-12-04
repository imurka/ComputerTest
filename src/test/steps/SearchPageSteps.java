package test.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.pages.SearchCompPage;

import java.util.List;

/**
 * Created by Ирина on 02.12.2016.
 */
public class SearchPageSteps {
    WebDriver driver;
    SearchCompPage searchCompPage;

    public SearchPageSteps(WebDriver driver) {
        this.driver = driver;
        searchCompPage = new SearchCompPage(driver);
    }

    public boolean verifyMessageNewCompIsCreated(String name) {
        return searchCompPage.getMessage().equals("Done! Computer " + name + " has been created");
    }

    public boolean verifyMessageCompIsUpdated(String name) {
        return searchCompPage.getMessage().equals("Done! Computer " + name + " has been updated");
    }

    public boolean verifyMessageCompIsDeleted() {
        return searchCompPage.getMessage().equals("Done! Computer has been deleted");
    }

    public CreateCompSteps goToAddCompPage() {
        return searchCompPage.goToAddCompPage();
    }

    public UpdateCompSteps goToUpdateCompPage(String name) {
        return searchCompPage.goToUpdateCompPage(name);
    }

    public void searchCompByName(String computerName) {
        searchCompPage.searchByName(computerName);
    }

    public boolean verifyOnePageSearchResults(String computerName) {
        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@id='main']//tbody//td/a"));//"//*[@id='main']//tbody//td[1]/a"));
        for (WebElement item : searchResults) {
            if (!item.getText().toLowerCase().contains(computerName.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyMultiplePageSearchResults(String computerName) {
        searchCompByName(computerName);
        int totalNumber = searchCompPage.getTotalSearchResult();
        boolean result = verifyOnePageSearchResults(computerName);
        for (int i = 0; i < totalNumber / 2; i++) {
            searchCompPage.clickNextBtn();
            result = verifyOnePageSearchResults(computerName);
        }
        String currentPaginationstatus = searchCompPage.getCurrentStatusPagination();
        String[] curPagStatus = currentPaginationstatus.split("\\s");
        int totalQuantity = Integer.parseInt(curPagStatus[curPagStatus.length - 1]);
        return result == true && totalQuantity == 0;
    }

    public boolean verifyMessageForEmptyResults(String computerName) {
        searchCompPage.searchByName(computerName);
        return searchCompPage.getMessageForEmtyResults().equals("Nothing to display");
    }

}
