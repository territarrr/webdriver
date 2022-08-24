package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='devsite-searchbox']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='devsite-searchbox']/input")
    private WebElement searchInput;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(HOMEPAGE_URL);
    }

    public void startSearch(String searchText) {
        searchButton.click();
        searchInput.sendKeys(searchText + "\n");
    }

}
