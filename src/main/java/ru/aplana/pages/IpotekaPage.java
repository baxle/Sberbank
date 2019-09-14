package ru.aplana.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static ru.aplana.steps.BaseSteps.driver;

public class IpotekaPage extends BasePage {

    private String oldValue;
    private JavascriptExecutor jse = (JavascriptExecutor)driver;

    @FindBy(xpath = "//*[contains(@class,'dcCalc_textfield')][contains(@id, 'estateCost')]")
    public WebElement propertyPrice;
    @FindBy(xpath = "//*[contains(@class,'dcCalc_textfield')][contains(@id, 'initialFee')]")
    public WebElement initialFee;
    @FindBy(xpath = "//input[@id='creditTerm']")
    public WebElement creditTerm;
    @FindBy(xpath = "//div[contains(@class, 'discounts')]//label[contains(@class, 'switch_checked')]")
    public WebElement salaryCard;
    @FindBy(xpath = "//div[contains(text(), 'Есть возможность подтвердить доход справкой')]")
    public  WebElement reference;
    @FindBy(xpath = "//div[@class='dcCalc_frame__discounts']/div[3]//span[@class='dcCalc_switch__control']")
    public WebElement referenceMenu;
    @FindBy(xpath = "//div[@class='dcCalc_frame__discounts']/div[5]//span[@class='dcCalc_switch__control']")
    public WebElement youngFamily;




    @FindBy(xpath = "//*[contains(@data-test-id, 'amountOfCredit')]")
    static
    public WebElement creditSum;
    @FindBy(xpath = "//*[contains(@data-test-id, 'monthlyPayment')]")
    public WebElement monthlyPayment;
    @FindBy(xpath = "//*[contains(@data-test-id, 'requiredIncome')]")
    static
    public WebElement requiredIncome;
    @FindBy(xpath = "//*[contains(@data-test-id, 'rate')]")
    public WebElement interestRate;



@Step("Заполнение всех полей в калькуляторе")
    public void fillAllFields(String propertyPriceVal, String initialFeeVal, String creditTermVal, boolean salaryCardVal, boolean youngFamilyVal) throws InterruptedException {

        fillPropertyPrice(propertyPriceVal);

        fillInitialFee(initialFeeVal);

        fillCreditTerm(creditTermVal);


        if(salaryCardVal==false){
            salaryCard.click();
        }

        waitForTextReference();

        if (youngFamilyVal==true){
            youngFamily.click();
        }



    }

    @Step("заполнение стоимости недвижимости значением {propertyPriceVal}")
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
    @Step("заполнение первоначального взноса значением {initialFeeVal}")
    public void fillInitialFee(String initialFeeVal) {

    /*  do{

            fillField(initialFee, initialFeeVal +"\n");
        }
        while (!(driver.findElement(By.xpath("//input[@id='creditTerm']")).getText()).equals(initialFeeVal));*/



      // fillField(initialFee, initialFeeVal+"\n");

        if(!initialFeeVal.equals(initialFee.getText())){
           /* fillField(initialFee, initialFeeVal+"\n");*/
            fillField(initialFee, initialFeeVal);
        }


           /* if((expectedPrice.equals(actualPrice)!=true)){
                initialFee.clear();
                initialFee.sendKeys(str1);
            }*/

    }

    @Step("заполнение срока кредита значением {creditTermVal}")
    public void fillCreditTerm(String creditTermVal) {
       /* fillField(creditTerm, creditTermVal + "\n");*/

        if(!(creditTermVal.equals(creditTerm.getText()))){
            fillField(creditTerm, creditTermVal+"\n");
        }

    }

    @Step("снимаем метку с наличия зарплатной карты сбербанка")
    public void offSalaryCard() {


        WebDriverWait wait = new WebDriverWait(driver, 30);
        oldValue = IpotekaPage.getRequiredIncome();

        Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !(IpotekaPage.getRequiredIncome().equals(oldValue));
            }
        };

        if(driver.findElement(By.xpath("//input[@data-test-id='paidToCard']")).isSelected()) {
            salaryCard.click();
            wait.until(valueChanged);
        }
    }

    @Step("ожидание появления \"есть возможность подтвердить доход справкой\"")
    public void waitForTextReference() {

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//div[contains(text(), 'Есть возможность подтвердить доход справкой')]")).isDisplayed();
            }
        });
    }

    @Step("ставим метку \"Молодая семья\"")
    public void onYoungFamily() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        oldValue = IpotekaPage.getRequiredIncome();

        Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !(IpotekaPage.getRequiredIncome().equals(oldValue));
            }
        };


        if (!youngFamily.isSelected()) {
            youngFamily.click();
            wait.until(valueChanged);
        }

    }


    @Step("снимаем метку с возможности подтвердить доход справкой")
    public void offReference(){

        jse.executeScript("window.scrollBy(0,150)");


        WebDriverWait wait = new WebDriverWait(driver, 30);
        oldValue = IpotekaPage.getRequiredIncome();

        Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !(IpotekaPage.getRequiredIncome().equals(oldValue));
            }
        };


        if(driver.findElement(By.xpath("//input[@data-test-id='canConfirmIncome']")).isSelected()){
            referenceMenu.click();
            wait.until(valueChanged);
        }

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

    @Step("сверка суммы кредита со значением {creditSumVal}")
    public void checkCreditSum(String creditSumVal){
        WebDriverWait wait = new WebDriverWait(driver, 30);


        assertEquals("Сумма кредита не соотвествует ожидаемой сумме", creditSumVal, creditSum.getText());
    }

    @Step("сверка ежемесячного платежа со значением {monthlyPaymentVal}")
    public void checkMonthlyPayment(String monthlyPaymentVal){
        assertEquals("Ежемесячный платеж не соотвествует ожидаемому платежу", monthlyPaymentVal, monthlyPayment.getText());
    }

    @Step("сверка необходимого дохода со значением {requiredIncomeVal}")
    public void checkRequiredIncome(String requiredIncomeVal){
        assertEquals("Необходимый доход не соотвествует ожидаемому доходу", requiredIncomeVal, requiredIncome.getText());
    }

    @Step("сверка процентной ставки со значением {interestRateVal}")
    public void checkInterestRate(String interestRateVal){
        assertEquals("Процентная ставка не соотвествует ожидаемой ставке", interestRateVal, interestRate.getText());
    }

    public void checkAllCount(){

    }

    public static String getRequiredIncome(){
        return requiredIncome.getText();
    }
}
