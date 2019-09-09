package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static steps.BaseSteps.driver;

public class IpotekaPage extends BasePage {
    @FindBy(xpath = "//*[contains(@class,'dcCalc_textfield')][contains(@id, 'estateCost')]")
    WebElement propertyPrice;
    @FindBy(xpath = "//*[contains(@class,'dcCalc_textfield')][contains(@id, 'initialFee')]")
    WebElement initialFee;
    @FindBy(xpath = "//input[@id='creditTerm']")
    WebElement creditTerm;
    @FindBy(xpath = "//div[contains(@class, 'discounts')]//label[contains(@class, 'switch_checked')]")
    WebElement salaryCard;
    @FindBy(xpath = "//div[contains(text(), 'Есть возможность подтвердить доход справкой')]")
    WebElement reference;
    @FindBy(xpath = "//div[@class='dcCalc_frame__discounts']/div[5]//span[@class='dcCalc_switch__control']")
    WebElement youngFamily;

    @FindBy(xpath = "//*[contains(@data-test-id, 'amountOfCredit')]")
    static
    WebElement creditSum;
    @FindBy(xpath = "//*[contains(@data-test-id, 'monthlyPayment')]")
    WebElement monthlyPayment;
    @FindBy(xpath = "//*[contains(@data-test-id, 'requiredIncome')]")
    static
    WebElement requiredIncome;
    @FindBy(xpath = "//*[contains(@data-test-id, 'rate')]")
    WebElement interestRate;


    private String oldValue;
    WebDriverWait wait = new WebDriverWait(driver, 30);






    public void fillAllFields(String propertyPriceVal, String initialFeeVal, String creditTermVal, boolean salaryCardVal, boolean youngFamilyVal) throws InterruptedException {
       // Thread.sleep(2000);
        fillPropertyPrice(propertyPriceVal);
       // Thread.sleep(2000);
        fillInitialFee(initialFeeVal);
       // Thread.sleep(2000);
        fillCreditTerm(creditTermVal);
       // Thread.sleep(2000);

        if(salaryCardVal==false){
            salaryCard.click();
        }

      //  Thread.sleep(2000);
        waitForTextReference();
     //   Thread.sleep(2000);

        if (youngFamilyVal==true){
            youngFamily.click();
        }



    }

    public void fillPropertyPrice(String propertyPriceVal) {

       /* Actions builder = new Actions(driver);
        builder.moveToElement(propertyPrice).build().perform();
        oldValue = getCreditSum();

        System.out.println(getCreditSum());
        Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !(getCreditSum()!=(oldValue));
            }
        };

        fillField(propertyPrice, propertyPriceVal + "\n");
        wait.until(valueChanged);
        System.out.println(getCreditSum());*/


      /*  Actions builder = new Actions(driver);
        builder.moveToElement(propertyPrice).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        oldValue = getCreditSum();

            Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
                @Override
                public Boolean apply(WebDriver webDriver) {
                    return !(getCreditSum().equals(oldValue));
                }
            };*/


        fillField(propertyPrice, propertyPriceVal);
          /*  wait.until(valueChanged);*/



    }

    public void fillInitialFee(String initialFeeVal) {

        do{

            fillField(initialFee, initialFeeVal );
        }
        while (!(driver.findElement(By.xpath("//input[@id='creditTerm']")).getText()).equals(initialFeeVal));



       /* fillField(initialFee, initialFeeVal + "\n");*/
    }

    public void fillCreditTerm(String creditTermVal) {
        fillField(creditTerm, creditTermVal + "\n");
    }

    public void offSalaryCard() {
        salaryCard.click();
    }

    public void waitForTextReference() {

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//div[contains(text(), 'Есть возможность подтвердить доход справкой')]")).isDisplayed();
            }
        });
    }

    public void onYoungFamily() {
        youngFamily.click();
    }

/*Проверить значение полей
Сумма кредита
2 122 000 ₽
Ежемесячный платеж
18 937 ₽
Необходимый доход
31 561 ₽
Процентная ставка
11% - тут ошибка (специально)
*/

    public void checkCreditSum(String creditSumVal){
        assertEquals("Сумма кредита не соотвествует ожидаемой сумме", creditSumVal, creditSum.getText());
    }

    public void checkMonthlyPayment(String monthlyPaymentVal){
        assertEquals("Ежемесячный платеж не соотвествует ожидаемому платежу", monthlyPaymentVal, monthlyPayment.getText());
    }

    public void checkRequiredIncome(String requiredIncomeVal){
        assertEquals("Необходимый доход не соотвествует ожидаемому доходу", requiredIncomeVal, requiredIncome.getText());
    }

    public void checkInterestRate(String interestRateVal){
        assertEquals("Процентная ставка не соотвествует ожидаемой ставке", interestRateVal, interestRate.getText());
    }

    public void checkAllCount(){

    }

    public static String getRequiredIncome(){
        return requiredIncome.getText();
    }
}
