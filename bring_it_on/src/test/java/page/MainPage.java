package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private WebDriver driver;

    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement newText;

    @FindBy(xpath = "//span[@data-select2-id='1']")
    private WebElement selectHighlighting;

    @FindAll(
            {
                    @FindBy(xpath = "//li[@class='select2-results__option']/ul/li")
            }
    )
    private List<WebElement> highlightingOptions;

    @FindBy(xpath = "//span[@data-select2-id='3']")
    private WebElement selectExpiration;

    @FindAll(
            {
                    @FindBy(xpath = "//li[@class='select2-results__option']")
            }
    )
    private List<WebElement> expirationOptions;

    @FindBy(xpath = "//input[@id='postform-name']")
    private WebElement pasteName;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement newPasteButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10));
        return this;
    }

    public void enterNewText(String name) {
        newText.sendKeys(name);
    }

    public void setSelectOption(WebElement select, List<WebElement> selectOptions, String optionValue) {
        select.click();
        for (WebElement selectOption : selectOptions) {
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(selectOption));
            if (selectOption.getText().trim().equals(optionValue.trim())) {
                selectOption.click();
                new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOf(selectOption));
                break;
            }
        }
    }

    public void enterHighlighting(String highlighting) {
        setSelectOption(selectHighlighting, highlightingOptions, highlighting);
    }

    public void enterExpiration(String expiration) {
        setSelectOption(selectExpiration, expirationOptions, expiration);
    }

    public void enterName(String name) {
        pasteName.sendKeys(name);
    }

    public CreatedPaste clickCreateNewPasteButton() {
        newPasteButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOf(newPasteButton));
        return new CreatedPaste(driver);
    }
}
