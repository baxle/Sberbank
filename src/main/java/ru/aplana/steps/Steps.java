package ru.aplana.steps;

import io.cucumber.java.Before;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import ru.aplana.pages.BasePage;
import ru.aplana.pages.IpotekaPage;
import ru.aplana.pages.MainPage;
import ru.aplana.steps.BaseSteps;
import util.TestProperties;

import java.util.concurrent.TimeUnit;

public class Steps extends BaseSteps {

    WebDriver driver;
    MainPage mainPage;
    IpotekaPage ipotekaPage;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", TestProperties.getInstance().getProperty("path.chrome"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TestProperties.getInstance().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        ipotekaPage = PageFactory.initElements(driver, IpotekaPage.class);
    }

    @Когда("на меню \"Ипотека\" наведен курсор")
    public void openMain() {
        mainPage.menu();
    }

    @Тогда("проверено, что открылось выпадающее меню")
    public void checkmenu() throws InterruptedException {
        mainPage.goToIpoteka();
    }

    @Когда("заполнено поле стоимость недвижимости значением (.*)")
    public void fillPropVal(String propertyPriceVal) {
        ipotekaPage.fillPropertyPrice(propertyPriceVal);
    }

    @Когда("заполнено поле первоначальный взнос значением (.*)")
    public void fillInitialFee(String initialFeeVal) {
        ipotekaPage.fillInitialFee(initialFeeVal);
    }

    @Когда("заполнено поле скрок кредита значением (.*)")
    public void fllPropVal(String creditTermVal) {
        ipotekaPage.fillCreditTerm(creditTermVal);
    }

    @Когда("выставляем галочку молодая семья")
    public void set() {
        ipotekaPage.offSalaryCard();
        ipotekaPage.waitForTextReference();
        ipotekaPage.onYoungFamily();
        ipotekaPage.offReference();
    }

    @Тогда("проверяем значение поля Сумма кредита (.*)")
    public void check1(String creditSumVal) {
        ipotekaPage.checkCreditSum(creditSumVal);
    }

    @Тогда("проверяем значение поля Ежемесячный платеж (.*)")
    public void check2(String monthlyPaymentVal) {
        ipotekaPage.checkMonthlyPayment(monthlyPaymentVal);
    }

    @Тогда("проверяем значение поля Необходимый доход (.*)")
    public void check3(String requiredIncomeVal) {
        ipotekaPage.checkRequiredIncome(requiredIncomeVal);
    }

    @Тогда("проверяем значение поля Процентная ставка (.*)")
    public void check4(String interestRateVal) {
        ipotekaPage.checkInterestRate(interestRateVal);
    }




}
