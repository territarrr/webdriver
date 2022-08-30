package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomCondition;

import java.time.Duration;

public class GenerateTmpEmailPage {
    private final int WAIT_TIME_IN_SECONDS = 30;
    private static final String HOMEPAGE_URL = "https://yopmail.com/ru/email-generator";
    private final WebDriver driver;

    @FindBy(xpath = "//button[@id='cprnd']")
    WebElement copyEmailButton;

    @FindBy(xpath = "//button/span[text()='Проверить почту']")
    WebElement checkEmailButton;

    public GenerateTmpEmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GenerateTmpEmailPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public void copyEmailButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_IN_SECONDS)).until(CustomCondition.isButtonEnabled(copyEmailButton));
        copyEmailButton.click();
    }

    public EmailListPage checkEmailButtonClick() {
        checkEmailButton.click();
        return new EmailListPage(driver);
    }

}
