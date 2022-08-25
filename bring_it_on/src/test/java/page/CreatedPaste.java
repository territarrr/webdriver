package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatedPaste {
    private final WebDriver driver;

    @FindBy(xpath = "//div[contains(@class,'highlighted-code')]/descendant::a")
    private WebElement resultHighlighting;

    @FindBy(xpath = "//textarea[contains(@class,'js-paste-raw')]")
    private WebElement resultRawPaste;

    public CreatedPaste(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle().split("-")[0].trim();
    }

    public String getHighlighting() {
        return resultHighlighting.getText();
    }

    public String getRawPaste() {
        return resultRawPaste.getText().trim();
    }

}
