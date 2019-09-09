import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.IpotekaPage;
import pages.MainPage;
import steps.BaseSteps;
import util.TestProperties;

import java.util.concurrent.TimeUnit;

public class SberTest {

    WebDriver driver;


    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", TestProperties.getInstance().getProperty("path.chrome"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TestProperties.getInstance().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BaseSteps.driver = driver;

    }


    @Test
    public void check() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.menu();
        mainPage.goToIpoteka();

        IpotekaPage ipotekaPage = new IpotekaPage();

       /*
        ipotekaPage.fillPropertyPrice("5180000");
        ipotekaPage.fillInitialFee("3058000");
        ipotekaPage.fillCreditTerm("30");
        ipotekaPage.offSalaryCard();
        ipotekaPage.waitForTextReference();
        ipotekaPage.onYoungFamily();*/


       ipotekaPage.fillAllFields("5180000", "3058000", "30", false, true );

       ipotekaPage.checkCreditSum("2 122 000 ₽");
       ipotekaPage.checkMonthlyPayment("18 937 ₽");
       ipotekaPage.checkRequiredIncome("31 561 ₽");
       ipotekaPage.checkInterestRate("11 %");


    }


}