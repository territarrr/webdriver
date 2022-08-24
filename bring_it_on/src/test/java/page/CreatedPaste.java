package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatedPaste {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='source']/ol")
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
        return resultHighlighting.getAttribute("class");
    }

    public String getRawPaste() {
        return resultRawPaste.getText().trim();
    }

}
