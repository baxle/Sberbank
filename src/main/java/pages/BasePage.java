package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;
import java.util.function.Function;

import static steps.BaseSteps.driver;


public class BasePage {
    private String oldValue;

    public BasePage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }

    public void fillField(WebElement field, String value) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        oldValue = IpotekaPage.getRequiredIncome();

        Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !(IpotekaPage.getRequiredIncome().equals(oldValue));
            }
        };

        field.click();
        field.clear();
        field.sendKeys(value);
        /*field.sendKeys(Keys.TAB);*/
        wait.until(valueChanged);


      /* WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(field));

        field.click();
        field.clear();

        field.sendKeys(value);*/

        //  field.sendKeys(Keys.TAB);*/
    }

}