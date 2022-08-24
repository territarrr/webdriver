package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class SearchResultPage {
    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='gsc-thumbnail-inside']/div[@class='gs-title']/a[@class='gs-title']/b")
    private List<WebElement> searchResults;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
    }

    public void goToResult(String resultName) {
        for (WebElement searchResult : searchResults) {
            if (searchResult.getText().equals(resultName)) {
                new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(searchResult)).click();
                break;
            }
        }
    }


}
