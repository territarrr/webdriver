package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page.CalculatorPage;
import page.MainPage;
import page.SearchResultPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CalculatorResultTest {
    class TestValue {
        final private String input;
        final private String result;

        public TestValue(String input, String result) {
            this.input = input;
            this.result = result;
        }
    }

    private WebDriver driver;
    TestValue numberOfInstances;
    TestValue vmClass;
    TestValue os;
    TestValue instanceType;
    TestValue region;
    TestValue localSSD;
    TestValue committedUsage;
    TestValue series;
    TestValue gpuType;
    TestValue gpuCount;
    CalculatorPage calculatorPage;

    @BeforeTest
    public void driverSetup() {
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        calculatorPage = mainPage.startSearch("Google Cloud Pricing Calculator").goToResult("Google Cloud Pricing Calculator");
        calculatorPage.clickComputeEngineTab();

        numberOfInstances = new TestValue("4", "4");
        vmClass = new TestValue("Regular", "Provisioning model: Regular");
        os = new TestValue("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)", "Operating System / Software: Free");
        instanceType = new TestValue("n1-standard-8 (vCPUs: 8, RAM: 30GB)", "Instance type: n1-standard-8\nCommitted Use Discount applied");
        region = new TestValue("Frankfurt (europe-west3)", "Region: Frankfurt");
        localSSD = new TestValue("2x375 GB", "Local SSD: 2x375 GiB\nCommitted Use Discount applied");
        committedUsage = new TestValue("1 Year", "Commitment term: 1 Year");
        series = new TestValue("N1", "N1");
        gpuType = new TestValue("NVIDIA Tesla V100", "NVIDIA Tesla V100");
        gpuCount = new TestValue("1", "1");

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
    }

    @Test
    public void compareVmClass() {
        assertEquals(vmClass.result, calculatorPage.getResultVM());
    }

    @Test
    public void compareOs() {
        assertEquals(os.result, calculatorPage.getResultOS());
    }

    @Test
    public void compareInstanceType() {
        assertEquals(instanceType.result, calculatorPage.getResultInstanceType());
    }

    @Test
    public void compareRegion() {
        assertEquals(region.result, calculatorPage.getResultRegion());
    }

    @Test
    public void compareLocalSSD() {
        assertEquals(localSSD.result, calculatorPage.getResultLocalSSD());
    }

    @Test
    public void compareCommittedUsage() {
        assertEquals(committedUsage.result, calculatorPage.getResultCommitmentTerm());
    }

    @AfterTest
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

}
