package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailListPage {

    private final int WAIT_TIME_IN_SECONDS = 30;
    private final WebDriver driver;
    @FindBy(xpath = "//button[@id='refresh']")
    WebElement refreshEmailsButton;

    @FindBy(xpath = "//iframe[@id='ifinbox']")
    WebElement inboxFrame;

    @FindBy(xpath = "//iframe[@id='ifmail']")
    WebElement emailFrame;

    @FindBy(xpath = "//div[@id='mail']/descendant::h3[2]")
    WebElement estimateEmailCost;

    public EmailListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openEmail() {
        while (!inboxFrame.isDisplayed()) {
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_IN_SECONDS)).until(ExpectedConditions.elementToBeClickable(refreshEmailsButton));
            refreshEmailsButton.click();
        }
    }

    public String getEstimateEmailCost() {
        driver.switchTo().frame(emailFrame);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_IN_SECONDS)).until(ExpectedConditions.visibilityOf(estimateEmailCost));
        return estimateEmailCost.getText().substring(estimateEmailCost.getText().indexOf("USD") + new String("USD").length()).trim();
    }
}
