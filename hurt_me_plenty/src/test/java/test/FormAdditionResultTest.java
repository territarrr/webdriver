package test;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.collections.Pair;
import page.CalculatorPage;
import page.MainPage;
import page.SearchResultPage;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FormAdditionResultTest {
    class TestValue {
        final private String input;
        final private String result;

        public TestValue(String input, String result) {
            this.input = input;
            this.result = result;
        }
    }
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void testCalculatorForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.startSearch("Google Cloud Pricing Calculator");

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.goToResult("Google Cloud Pricing Calculator");

        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.clickComputeEngineTab();

        TestValue numberOfInstances = new TestValue("4", "4");
        TestValue vmClass = new TestValue("Regular", "Provisioning model: Regular");
        TestValue os = new TestValue("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)", "Operating System / Software: Free");
        TestValue instanceType = new TestValue("n1-standard-8 (vCPUs: 8, RAM: 30GB)", "Instance type: n1-standard-8\nCommitted Use Discount applied");
        TestValue region = new TestValue("Frankfurt (europe-west3)", "Region: Frankfurt");
        TestValue localSSD = new TestValue("2x375 GB", "Local SSD: 2x375 GiB\nCommitted Use Discount applied");
        TestValue committedUsage = new TestValue("1 Year", "Commitment term: 1 Year");
        TestValue series = new TestValue("N1", "N1");
        TestValue gpuType = new TestValue("NVIDIA Tesla V100", "NVIDIA Tesla V100");
        TestValue gpuCount = new TestValue("1", "1");

        calculatorPage.enterNumberOfInstances(numberOfInstances.input);
        calculatorPage.enterOS(os.input);
        calculatorPage.enterVMClass(vmClass.input);
        calculatorPage.enterSeries(series.input);
        calculatorPage.enterMachineType(instanceType.input);
        calculatorPage.setCheckedAddGPUCheckbox();
        calculatorPage.enterGPUType(gpuType.input);
        calculatorPage.enterGPUCount(gpuCount.input);
        calculatorPage.enterLocalSSD(localSSD.input);
        calculatorPage.enterDatacenterLocation(region.input);
        calculatorPage.enterCommittedUsage(committedUsage.input);
        calculatorPage.clickButtonAddToEstimte();
        
        assertEquals(vmClass.result,calculatorPage.getResultVM());
        assertEquals(os.result, calculatorPage.getResultOS());
        assertEquals(instanceType.result, calculatorPage.getResultInstanceType());
        assertEquals(region.result, calculatorPage.getResultRegion());
        assertEquals(localSSD.result, calculatorPage.getResultLocalSSD());
        assertEquals(committedUsage.result, calculatorPage.getResultCommitmentTerm());
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
//        driver.quit();
//        driver = null;
    }

}
