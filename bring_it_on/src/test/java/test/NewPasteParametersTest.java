package test;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page.CreatedPaste;
import page.MainPage;

public class NewPasteParametersTest {
    private WebDriver driver;
    private String pasteText = "git config --global user.name  \"New Sheriff in Town\"\n" + "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" + "git push origin master --force";
    private String pasteName = "how to gain dominance among developers";
    private String pasteHighlighting = "Bash";
    private String pasteExpiration = "10 Minutes";
    CreatedPaste createdPaste;

    @BeforeTest
    public void createNewPaste() {
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.enterNewText(pasteText);
        mainPage.enterHighlighting(pasteHighlighting);
        mainPage.enterExpiration(pasteExpiration);
        mainPage.enterName(pasteName);
        createdPaste = mainPage.clickCreateNewPasteButton();
    }

    @Test
    public void compareHighlighting() {
        Assert.assertEquals(pasteHighlighting, createdPaste.getHighlighting());
    }

    @Test
    public void compareTitle() {
        Assert.assertEquals(pasteName, createdPaste.getPageTitle());
    }

    @Test
    public void compareText() {
        Assert.assertEquals(pasteText, createdPaste.getRawPaste());
    }

    @AfterTest
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

}
