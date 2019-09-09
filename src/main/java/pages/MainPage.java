package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static steps.BaseSteps.driver;

public class MainPage extends BasePage {
    @FindBy(xpath = "//span[.='Ипотека']")
    WebElement menu;

    @FindBy(xpath = "//li[contains(@class, 'lg-menu__item_hovered')]//a[.='Ипотека на готовое жильё']")
    WebElement ipoteka;

    @FindBy(xpath = "//a[.='Условия и требования']")
    WebElement ipotekaNew;

    @FindBy(xpath = "//*[@class='kit-button__text'][contains(text(), 'Рассчитать ипотеку')]")
    WebElement ipotekaButton;


    public void menu(){
        Actions builder = new Actions(driver);
        builder.moveToElement(menu).build().perform();
    }

    public void goToIpoteka(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(ipoteka));
        ipoteka.click();

        wait.until(ExpectedConditions.elementToBeClickable(ipotekaNew));
        ipotekaNew.click();

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");
        ipotekaButton.click();


    }



}

