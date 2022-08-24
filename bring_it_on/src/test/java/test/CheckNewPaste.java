package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CreatedPaste;
import page.MainPage;


import static org.junit.Assert.*;

public class CheckNewPaste {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup() {
        driver = new ChromeDriver();
    }


    public void createNewPate(String pasteText, String pasteHighlighting, String pasteExpiration, String pasteName) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.enterNewText(pasteText);
        mainPage.enterHighlighting(pasteHighlighting);
        mainPage.enterExpiration(pasteExpiration);
        mainPage.enterName(pasteName);
        mainPage.clickCreateNewPasteButton();
    }

    @Test
    public void checkNewPasteCreation() {
        String pasteText = "git config --global user.name  \"New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "git push origin master --force";
        String pasteName = "how to gain dominance among developers";
        String pasteHighlighting = "Bash";
        String pasteExpiration = "10 Minutes";

        createNewPate(pasteText, pasteHighlighting, pasteExpiration, pasteName);
        CreatedPaste createdPaste = new CreatedPaste(driver);
        assertEquals(createdPaste.getPageTitle(), pasteName);
        assertEquals(createdPaste.getHighlighting(), pasteHighlighting.toLowerCase());
        assertEquals(createdPaste.getRawPaste(), pasteText);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
//        driver.quit();
//        driver = null;
    }

}
